import React from 'react'
import { Container, Fade } from 'reactstrap'
import classnames from 'classnames'

import { NodeRelevanceUpdatedEvent } from 'model/event/RecordEvent'
import { TableDefinition } from 'model/ui/TableDefinition'
import { MultipleFieldsetDefinition } from 'model/ui/MultipleFieldsetDefinition'

import FormItem from './FormItem'
import AbstractFormComponent from './AbstractFormComponent'

const FormItemsItem = (props) => {
  const { itemDef, parentEntity, fullSize } = props

  const nodeDefinition = itemDef.attributeDefinition || itemDef.entityDefinition
  const relevant = parentEntity.childrenRelevanceByDefinitionId[nodeDefinition.id]
  const visible = relevant || !nodeDefinition.hideWhenNotRelevant

  return (
    visible && (
      <Fade in={visible} className={classnames('form-item-external-wrapper', { 'full-height': fullSize })}>
        <FormItem parentEntity={parentEntity} itemDef={itemDef} fullSize={fullSize} />
      </Fade>
    )
  )
}

FormItemsItem.defaultProps = {
  fullSize: false,
}

export default class FormItems extends AbstractFormComponent {
  onRecordEvent(event) {
    super.onRecordEvent(event)

    const { parentEntity } = this.props

    if (event instanceof NodeRelevanceUpdatedEvent && event.isRelativeToNode(parentEntity)) {
      this.forceUpdate()
    }
  }

  render() {
    const { itemDefs, parentEntity } = this.props

    const onlyOneMultipleEntity =
      itemDefs.length === 1 &&
      (itemDefs[0] instanceof TableDefinition || itemDefs[0] instanceof MultipleFieldsetDefinition)

    return onlyOneMultipleEntity ? (
      <FormItemsItem itemDef={itemDefs[0]} parentEntity={parentEntity} fullSize />
    ) : itemDefs.length > 0 ? (
      <Container className="formItems">
        {itemDefs.map((itemDef) => (
          <FormItemsItem key={itemDef.id} itemDef={itemDef} parentEntity={parentEntity} />
        ))}
      </Container>
    ) : null
  }
}
