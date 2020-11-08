import Serializable from '../Serializable'

export class Node extends Serializable {
  record
  parent
  definition
  id

  constructor(record, definition, parent) {
    super()
    this.record = record
    this.definition = definition
    this.parent = parent
  }

  get survey() {
    return this.record.survey
  }

  isRoot() {
    return this.parent === null
  }

  fillFromJSON(jsonObj) {
    super.fillFromJSON(jsonObj)
  }

  calculatePath() {
    const { definition, parent, index } = this

    return (
      (parent ? parent.path : '') +
      '/' +
      definition.name +
      (definition.multiple && parent ? '[' + (index + 1) + ']' : '')
    )
  }

  calculateIndex() {
    return this.parent.childrenByDefinitionId[this.definition.id].indexOf(this)
  }

  updatePath() {
    this.index = this.calculateIndex()
    this.path = this.calculatePath()
  }

  getAncestorByDefinitionId(defId) {
    let currentParent = this.parent
    while (!currentParent.isRoot() && currentParent.definition.id !== defId) {
      currentParent = currentParent.parent
    }
    return currentParent.definition.id === defId ? currentParent : null
  }
}
