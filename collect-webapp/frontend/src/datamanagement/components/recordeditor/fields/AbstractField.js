import { Component } from 'react'
import { debounce } from 'throttle-debounce'

import { AttributeUpdatedEvent, RecordEvent } from 'model/event/RecordEvent'
import EventQueue from 'model/event/EventQueue'
import ServiceFactory from 'services/ServiceFactory'

export default class AbstractField extends Component {
  constructor() {
    super()

    this.state = {
      dirty: false,
      value: null,
      errors: null,
      warnings: null,
    }

    this.attributeUpdatedDebounced = null
    this.handleRecordEventReceived = this.handleRecordEventReceived.bind(this)
    this.onAttributeUpdate = this.onAttributeUpdate.bind(this)

    EventQueue.subscribe(RecordEvent.TYPE, this.handleRecordEventReceived)
  }

  componentDidMount() {
    this.updateStateFromProps()
  }

  componentWillUnmount() {
    EventQueue.unsubscribe(RecordEvent.TYPE, this.handleRecordEventReceived)
  }

  updateStateFromProps() {
    this.setState({ dirty: false, value: this.extractValueFromProps(), ...this.extractValidationFromProps() })
  }

  extractValueFromProps() {
    return null
  }

  extractValidationFromProps() {
    const attr = this.getSingleAttribute()
    let errors = null,
      warnings = null
    if (attr) {
      const { errors: errorsArray, warnings: warningsArray } = attr.validationResults
      errors = errorsArray ? errorsArray.join('; ') : null
      warnings = warningsArray ? warningsArray.join('; ') : null
    }
    return { errors, warnings }
  }

  getSingleAttribute(parentEntity) {
    if (!parentEntity) {
      parentEntity = this.props.parentEntity
    }
    if (parentEntity) {
      const { fieldDef } = this.props
      const attrDef = fieldDef.attributeDefinition
      if (attrDef.multiple) {
        throw new Error('Expected single attribute, found multiple: ' + attrDef.name)
      } else {
        return parentEntity.getSingleChild(attrDef.id)
      }
    }
  }

  onAttributeUpdate({ value, debounced = true }) {
    this.setState({ value, dirty: true })

    const attr = this.getSingleAttribute()
    if (this.attributeUpdatedDebounced) {
      this.attributeUpdatedDebounced.cancel()
    }
    this.attributeUpdatedDebounced = debounce(debounced ? 1000 : 0, false, () =>
      ServiceFactory.commandService.updateAttribute(attr, value)
    )
    this.attributeUpdatedDebounced()
  }

  handleRecordEventReceived(event) {
    const { fieldDef, parentEntity } = this.props
    if (!parentEntity) {
      return
    }
    if (event instanceof AttributeUpdatedEvent) {
      if (
        event.recordId === parentEntity.record.id &&
        event.recordStep === parentEntity.record.step &&
        event.parentEntityPath === parentEntity.path &&
        Number(event.definitionId) === fieldDef.attributeDefinitionId
      ) {
        this.handleAttributeUpdatedEvent(event)
      }
    }
  }

  handleAttributeUpdatedEvent(_) {
    this.updateStateFromProps()
  }
}
