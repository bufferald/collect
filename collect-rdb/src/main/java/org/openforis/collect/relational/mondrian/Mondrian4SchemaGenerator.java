package org.openforis.collect.relational.mondrian;


import java.util.ArrayList;
import java.util.List;

import mondrian.olap.MondrianDef;
import mondrian.olap.MondrianDef.Attribute;
import mondrian.olap.MondrianDef.Attributes;
import mondrian.olap.MondrianDef.Column;
import mondrian.olap.MondrianDef.Cube;
import mondrian.olap.MondrianDef.Dimension;
import mondrian.olap.MondrianDef.DimensionLink;
import mondrian.olap.MondrianDef.DimensionLinks;
import mondrian.olap.MondrianDef.Dimensions;
import mondrian.olap.MondrianDef.FactLink;
import mondrian.olap.MondrianDef.ForeignKey;
import mondrian.olap.MondrianDef.ForeignKeyLink;
import mondrian.olap.MondrianDef.Hierarchies;
import mondrian.olap.MondrianDef.Hierarchy;
import mondrian.olap.MondrianDef.Level;
import mondrian.olap.MondrianDef.Link;
import mondrian.olap.MondrianDef.Measure;
import mondrian.olap.MondrianDef.MeasureGroup;
import mondrian.olap.MondrianDef.MeasureGroups;
import mondrian.olap.MondrianDef.Measures;
import mondrian.olap.MondrianDef.NoLink;
import mondrian.olap.MondrianDef.PhysicalSchema;
import mondrian.olap.MondrianDef.Table;
import mondrian.rolap.RolapAggregator;

import org.openforis.collect.model.CollectSurvey;
import org.openforis.collect.relational.model.CodeTable;
import org.openforis.collect.relational.model.CodeValueFKColumn;
import org.openforis.collect.relational.model.DataColumn;
import org.openforis.collect.relational.model.DataTable;
import org.openforis.collect.relational.model.PrimaryKeyConstraint;
import org.openforis.collect.relational.model.ReferentialConstraint;
import org.openforis.collect.relational.model.RelationalSchema;
import org.openforis.collect.relational.model.RelationalSchemaConfig;
import org.openforis.collect.relational.model.RelationalSchemaGenerator;
import org.openforis.collect.relational.model.UniquenessConstraint;
import org.openforis.collect.relational.util.CodeListTables;
import org.openforis.idm.metamodel.AttributeDefinition;
import org.openforis.idm.metamodel.CodeAttributeDefinition;
import org.openforis.idm.metamodel.DateAttributeDefinition;
import org.openforis.idm.metamodel.EntityDefinition;
import org.openforis.idm.metamodel.FieldDefinition;
import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.metamodel.NodeLabel;

/**
 * 
 * @author S. Ricci
 *
 */
public class Mondrian4SchemaGenerator {

	private CollectSurvey survey;
	private RelationalSchemaConfig rdbConfig;
	private RelationalSchema rdbSchema;

	public Mondrian4SchemaGenerator(CollectSurvey survey, RelationalSchemaConfig rdbConfig) {
		this.survey = survey;
		this.rdbConfig = rdbConfig;
		
		init();
	}

	private void init() {
		RelationalSchemaGenerator schemaGenerator = new RelationalSchemaGenerator(rdbConfig);
		rdbSchema = schemaGenerator.generateSchema(survey, survey.getName());
	}
	
	public MondrianDef.Schema generateSchema() {
		MondrianDef.Schema schema = new MondrianDef.Schema();
		schema.metamodelVersion = "4.0";
		schema.name = survey.getName();
		PhysicalSchema physicalSchema = generatePhysicalSchema();
		schema.children.add(physicalSchema);
		
		List<EntityDefinition> rootEntityDefinitions = survey.getSchema().getRootEntityDefinitions();
		for (EntityDefinition rootEntityDef : rootEntityDefinitions) {
			schema.children.addAll(createCubes(rootEntityDef));
		}
		return schema;
	}

	private List<Cube> createCubes(EntityDefinition entityDef) {
		List<Cube> result = new ArrayList<MondrianDef.Cube>();
		DataTable dataTable = rdbSchema.getDataTable(entityDef);

		Cube cube = new Cube();
		cube.name = entityDef.getName();
		MeasureGroups measureGroups = new MondrianDef.MeasureGroups();
		MeasureGroup measureGroup = new MeasureGroup();
		measureGroup.name = cube.getName();
		Measures measures = new Measures();
		Measure measure = new Measure();
		measure.name = entityDef.getName() + " count";
		measure.column = dataTable.getPrimaryKeyColumn().getName();
		measure.aggregator = RolapAggregator.Count.name;
		measure.table = dataTable.getName();
		measures.list().add(measure);
		measureGroup.children.add(measures);
		measureGroup.table = dataTable.getName();
		DimensionLinks dimensionLinks = new DimensionLinks();
		measureGroup.children.add(dimensionLinks);
		measureGroups.list().add(measureGroup);
		cube.children.add(measureGroups);
		
		Dimensions dimensions = new Dimensions();
		
		for (NodeDefinition def : entityDef.getChildDefinitions()) {
			if (def instanceof AttributeDefinition) {
				AttributeDefinition attrDef = (AttributeDefinition) def;
				Dimension dimension = createDimension(dataTable, attrDef);
				if (dimension != null) {
					dimensions.list().add(dimension);
					//add dimension link
					DimensionLink dimensionLink = createDimensionLink(dimension, attrDef);
					dimensionLinks.list().add(dimensionLink);
				}
			}
		}
		cube.children.add(dimensions);
		result.add(cube);
		
		for (NodeDefinition def : entityDef.getChildDefinitions()) {
			if (def instanceof EntityDefinition && def.isMultiple()) {
				result.addAll(createCubes((EntityDefinition) def));
			}
		}
		return result;
	}

	private DimensionLink createDimensionLink(Dimension dimension, AttributeDefinition attrDef) {
		DimensionLink dimensionLink;
		if (attrDef instanceof CodeAttributeDefinition) {
			if (((CodeAttributeDefinition) attrDef).getList().isExternal()) {
				dimensionLink = new FactLink();
			} else {
				ForeignKeyLink fkLink = new ForeignKeyLink();
				DataTable dataTable = rdbSchema.getDataTable(attrDef.getParentEntityDefinition());
				CodeValueFKColumn fkColumn = dataTable.getForeignKeyCodeColumn((CodeAttributeDefinition) attrDef);
				fkLink.foreignKeyColumn = fkColumn.getName();
				dimensionLink = fkLink;
			}
		} else {
			dimensionLink = new FactLink();
		}
		dimensionLink.dimension = dimension.name;
		return dimensionLink;
	}

	private Dimension createDimension(DataTable dataTable, AttributeDefinition attrDefn) {
		List<Attribute> attrs = getDimensionAttributes(dataTable, attrDefn);
		if (attrs.isEmpty()) {
			return null;
		} else {
			Dimension dimension = new Dimension();
			dimension.name = attrDefn.getName();
			dimension.caption = getDimensionCaption(attrDefn);
			dimension.key = attrDefn.getMainFieldName();
			dimension.table = dataTable.getName();
			
			Attributes attributes = new Attributes();
			attributes.list().addAll(attrs);
			dimension.children.add(attributes);

			List<Level> hierarchyLevels = getHierarchyLevels(attrDefn);
			if (! hierarchyLevels.isEmpty()) {
				Hierarchies hierarchies = new Hierarchies();
				Hierarchy hierarchy = new Hierarchy();
				hierarchy.name = attrDefn.getName();
				hierarchy.children.addAll(hierarchyLevels);
				hierarchies.list().add(hierarchy);
				dimension.children.add(hierarchies);
			}
			return dimension;
		}
	}

	private List<Attribute> getDimensionAttributes(DataTable dataTable,
			AttributeDefinition attrDefn) {
		List<Attribute> attributes = new ArrayList<Attribute>();
		
		if (attrDefn instanceof CodeAttributeDefinition && ! ((CodeAttributeDefinition) attrDefn).getList().isExternal()) {
			CodeAttributeDefinition codeAttrDefn = (CodeAttributeDefinition) attrDefn;
			CodeTable codeListTable = rdbSchema.getCodeListTable(codeAttrDefn);
			String codeListTableName = codeListTable.getName();
			Attribute attribute = new Attribute();
			FieldDefinition<String> codeFieldDef = codeAttrDefn.getCodeFieldDefinition();
			attribute.name = codeFieldDef.getName();
			attribute.caption = getAttributeCaption(codeFieldDef);
			attribute.table = codeListTableName;
			attribute.keyColumn = CodeListTables.getCodeColumnName(rdbConfig, codeListTableName);
			attribute.nameColumn = CodeListTables.getLabelColumnName(rdbConfig, codeListTableName);
			attributes.add(attribute);
		} else if (attrDefn.hasMainField()) {
			for (FieldDefinition<?> fieldDef : attrDefn.getFieldDefinitions()) {
				DataColumn col = dataTable.getDataColumn(fieldDef);
				if (col != null) {
					String fieldName = fieldDef.getName();
					Attribute attribute = new Attribute();
					attribute.name = fieldName;
					attribute.caption = getAttributeCaption(fieldDef);
//					attribute.table = dataTable.getName();
//					attribute.hasHierarchy = false;
					attribute.keyColumn = col.getName();
					if (attrDefn instanceof DateAttributeDefinition) {
						attribute.levelType = getDateFieldLevelType(fieldName);
					}
					attributes.add(attribute);
				}
			}
		}
		return attributes;
	}

	private List<Level> getHierarchyLevels(
			AttributeDefinition attrDef) {
		List<Level> levels = new ArrayList<Level>();
//		if (attrDef instanceof DateAttributeDefinition) {
//			for (FieldDefinition<?> fieldDef : attrDef.getFieldDefinitions()) {
//				String fieldName = fieldDef.getName();
//				Level level = new Level();
//				level.name = level.attribute = fieldName;
//				levels.add(level);
//			}
//		} else if (attrDef.hasMainField()) {
//			Level level = new Level();
//			level.name = level.attribute = attrDef.getMainFieldName();
//			levels.add(level);
//		}
		return levels;
	}

	private String getDateFieldLevelType(String name) {
		return null;
	}

	private PhysicalSchema generatePhysicalSchema() {
		PhysicalSchema physicalSchema = new PhysicalSchema();
		for (org.openforis.collect.relational.model.Table<?> rdbTable : rdbSchema.getTables()) {
			Table table = new Table();
			table.name = rdbTable.getName();
			PrimaryKeyConstraint pkConstraint = rdbTable.getPrimaryKeyConstraint();
			table.keyColumn = pkConstraint.getPrimaryKeyColumn().getName();
			physicalSchema.children.add(table);
			
			//add foreign keys
			for (ReferentialConstraint referentialConstraint : rdbTable.getReferentialContraints()) {
				UniquenessConstraint referencedKey = referentialConstraint.getReferencedKey();
				org.openforis.collect.relational.model.Table<?> referencedRdbTable = referencedKey.getTable();
				Link link = new Link();
				link.source = rdbTable.getName();
				link.target = referencedRdbTable.getName();
				ForeignKey foreignKey = new ForeignKey();
				for (org.openforis.collect.relational.model.Column<?> referencedRdbColumn : referencedKey.getColumns()) {
					Column fkColumn = new Column();
					fkColumn.name = referencedRdbColumn.getName();
					foreignKey.list().add(fkColumn);
				}
				link.foreignKey = foreignKey;
				physicalSchema.children.add(link);
			}
		}
		return physicalSchema;
	}
	
	private String extractLabel(NodeDefinition nodeDef) {
		String attrLabel = nodeDef.getLabel(NodeLabel.Type.INSTANCE);
		if (attrLabel == null) {
			attrLabel = nodeDef.getName();
		}
		return attrLabel;
	}
	
	private String getDimensionCaption(AttributeDefinition attrDefn) {
		return String.format("%s [%s]", extractLabel(attrDefn), attrDefn.getName());
	}

	private String getAttributeCaption(FieldDefinition<?> fieldDef) {
		return extractLabel(fieldDef.getAttributeDefinition()) + " - " + fieldDef.getName();
	}

}