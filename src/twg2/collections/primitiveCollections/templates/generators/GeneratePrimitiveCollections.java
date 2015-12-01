package twg2.collections.primitiveCollections.templates.generators;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.stringtemplate.v4.ST;

import twg2.collections.primitiveCollections.templates.MapInfo;
import twg2.collections.primitiveCollections.templates.PrimitiveListInfo;
import twg2.primitiveIoTypes.JPrimitiveType;
import twg2.template.codeTemplate.ClassInfo;
import twg2.template.codeTemplate.ClassTemplate;
import twg2.template.codeTemplate.ClassTemplateBuilder;
import twg2.template.codeTemplate.TemplateNames;
import twg2.template.codeTemplate.primitiveTemplate.PrimitiveClassAndType;
import twg2.template.codeTemplate.primitiveTemplate.PrimitiveTemplates;
import twg2.template.codeTemplate.primitiveTemplate.PrimitiveTypeClassTemplate;
import twg2.template.codeTemplate.render.STTemplates;
import twg2.template.codeTemplate.render.StringTemplatesUtil;
import twg2.template.codeTemplate.render.TemplateImports;
import twg2.template.codeTemplate.render.TemplateWriters;

/**
 * @author TeamworkGuy2
 * @since 2014-12-20
 */
public class GeneratePrimitiveCollections {
	private static List<Class<?>> primitiveTypes = Arrays.asList(Character.TYPE, Integer.TYPE, Float.TYPE, Long.TYPE, Double.TYPE);
	private static String templateDir = "src/twg2/collections/primitiveCollections/templates/";
	private static String templatePkg = "twg2.collections.primitiveCollections";


	private static <T extends PrimitiveTypeClassTemplate> void genBasicPrimitiveTmpls(String templateFileName, String templateName, Function<Class<?>, T> supplier) {
		TemplateWriters.renderPrimitiveClassTemplates(templateFileName, templateName, TemplateImports.emptyInst(), primitiveTypes, (type) -> {
			T tmpl = supplier.apply(type);
			Map.Entry<PrimitiveClassAndType, Object> entry = new AbstractMap.SimpleImmutableEntry<>(new PrimitiveClassAndType(tmpl, tmpl), tmpl);
			return entry;
		});
	}


	public static final void generatePrimitiveListReadOnly() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveListReadOnly.stg", "PrimitiveListReadOnly", (type) -> {
			return ClassTemplateBuilder.of(PrimitiveTemplates.ofType(type, "$Type$ListReadOnly", pkgName)).implement("java.util.RandomAccess", "$Type$Searchable").getTemplate();
		});
	}


	public static final void generatePrimitiveSearchable() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveSearchable.stg", "PrimitiveSearchable", (type) -> {
			return PrimitiveTemplates.ofType(type, "$Type$Searchable", pkgName);
		});
	}


	public static final void generatePrimitiveList() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveList.stg", "PrimitiveList", (type) -> {
			return ClassTemplateBuilder.of(PrimitiveTemplates.ofType(type, new PrimitiveTypeClassTemplate(), "$Type$List", pkgName)).implement("$Type$ListReadOnly").getTemplate();
		});
	}


	public static final void generatePrimitiveArrayList() throws IOException {
		String pkgName = templatePkg;
		String iterWrapper = "$type$IteratorWrapper";
		String arrayListIter = "$type$ArrayListIterator";

		genBasicPrimitiveTmpls(templateDir + "PrimitiveArrayList.stg", "PrimitiveArrayList", (type) -> {
			return PrimitiveTemplates.ofType(type, PrimitiveListInfo.iteratorTypes(type, iterWrapper, arrayListIter), "$Type$ArrayList", pkgName);
		});
	}


	public static final void generatePrimitiveSortedList() throws IOException {
		String pkgName = templatePkg;
		String iterWrapper = "$type$IteratorWrapper";
		String listSortedIter = "$type$ListSortedIterator";

		genBasicPrimitiveTmpls(templateDir + "PrimitiveSortedList.stg", "PrimitiveSortedList", (type) -> {
			return PrimitiveTemplates.ofType(type, PrimitiveListInfo.iteratorTypes(type, iterWrapper, listSortedIter), "$Type$ListSorted", pkgName);
		});
	}


	public static final void generatePrimitiveBag() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveBag.stg", "PrimitiveBag", (type) -> {
			return PrimitiveTemplates.ofType(type, new PrimitiveListInfo(type), "$Type$Bag", pkgName);
		});
	}


	public static final void generatePrimitiveViews() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveArrayView.stg", "PrimitiveArrayView", (type) -> {
			return PrimitiveTemplates.ofType(type, new ArrayViewInfo(type, "o == objs[i]", true), "$Type$ArrayView", pkgName);
		});
	}


	public static final void generatePrimitiveMapsReadOnly() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveMapReadOnly.stg", "PrimitiveMapReadOnly", (type) -> {
			return PrimitiveTemplates.ofType(type, new MapInfo.MapType(type, "T", "T", ".equals", "null"), "$Type$MapReadOnly", pkgName);
		});
	}


	public static final void generatePrimitiveMaps() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveSortedMap.stg", "PrimitiveSortedMap", (type) -> {
			return ClassTemplateBuilder.of(PrimitiveTemplates.ofType(type, new MapInfo.MapType(type, "T", "T", ".equals", "null"), "$Type$MapSorted", pkgName)).implement("$Type$MapReadOnly<T>").getTemplate();
		});
	}


	public static final void generatePrimitiveViewHandles() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveArrayViewHandle.stg", "PrimitiveArrayViewHandle", (type) -> {
			return ClassTemplateBuilder.of(PrimitiveTemplates.ofType(type, new PrimitiveIteratorInfo(type, "$Type$ArrayView"), "$Type$ArrayViewHandle", pkgName)).getTemplate();
		});
	}


	public static final void generatePrimitiveIteratorsReadOnly() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveIteratorReadOnly.stg", "PrimitiveIteratorReadOnly", (type) -> {
			return PrimitiveTemplates.ofType(type, new PrimitiveTypeClassTemplate(type), "$Type$IteratorReadOnly", pkgName);
		});
	}


	public static final void generatePrimitiveIterators() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveIterator.stg", "PrimitiveIterator", (type) -> {
			return ClassTemplateBuilder.of(PrimitiveTemplates.ofType(type, new PrimitiveTypeClassTemplate(type), "$Type$Iterator", pkgName)).implement("$Type$IteratorReadOnly").getTemplate();
		});
	}


	public static final void generatePrimitiveIteratorImpls() throws IOException {
		String pkgName = templatePkg;

		List<PrimitiveIteratorInfo> infos = new ArrayList<>();
		Class<?>[] classTypes = { Character.TYPE, Integer.TYPE, Float.TYPE, Long.TYPE, Double.TYPE };

		for(Class<?> classType : classTypes) {
			infos.add(generatePrimitiveIteratorInfo(classType, "$type$[]", "length", "[", "]", true, false, "mod", "$type$ArrayIterator", "$Type$Iterator", pkgName));
		}

		for(Class<?> classType : classTypes) {
			infos.add(generatePrimitiveIteratorInfo(classType, "$Type$ArrayList", "size()", ".get(", ")", false, true, "col.mod", "$type$ArrayListIterator", "$Type$Iterator", pkgName));
		}

		for(Class<?> classType : classTypes) {
			infos.add(generatePrimitiveIteratorInfo(classType, "$Type$ListSorted", "size()", ".get(", ")", false, true, "col.mod", "$type$ListSortedIterator", "$Type$Iterator", pkgName));
		}

		ST stTmpl = STTemplates.fromFile(templateDir + "PrimitiveIteratorImpls.stg", "PrimitiveIteratorImpls", TemplateImports.emptyInst());
		for(ClassInfo clsInfo : infos.toArray(new PrimitiveIteratorInfo[infos.size()])) {
			StringTemplatesUtil.renderClass(stTmpl, clsInfo, "var", clsInfo);
		}
	}


	public static final PrimitiveIteratorInfo generatePrimitiveIteratorInfo(Class<?> primitiveType, String collectionTypeTmpl,
			String sizeGetter, String getterStart, String getterEnd, boolean hasOwnMod, boolean hasCollMod, String modGetter, String classNameTmpl, String parentClassNameTmpl, String packageName) {
		PrimitiveIteratorInfo tmpl = PrimitiveTemplates.ofType(primitiveType, new PrimitiveIteratorInfo(), "", packageName);
		String typeNameUpper = tmpl.typeShortTitleCase;
		String typeNameLower = tmpl.typeShort;
		tmpl.className = classNameTmpl.replace("$type$", tmpl.typeShortTitleCase);
		tmpl.collectionType = collectionTypeTmpl.replace("$type$", typeNameLower).replace("$Type$", typeNameUpper);
		tmpl.implementClassNames = Arrays.asList(parentClassNameTmpl.replace("$type$", typeNameLower).replace("$Type$", typeNameUpper));
		tmpl.sizeGetter = sizeGetter;
		tmpl.getterStart = getterStart;
		tmpl.getterEnd = getterEnd;
		tmpl.modGetter = modGetter;
		tmpl.hasOwnMod = hasOwnMod;
		tmpl.hasCollMod = hasCollMod;

		return tmpl;
	}


	public static final void generatePrimitiveIteratorWrappers() throws IOException {
		String pkgName = templatePkg;

		genBasicPrimitiveTmpls(templateDir + "PrimitiveIteratorWrapper.stg", "PrimitiveIteratorWrapper", (type) -> {
			return PrimitiveTemplates.ofType(type, new WrapperTemplateInfo(type), "$Type$IteratorWrapper", pkgName);
		});
	}




	/**
	 * @author TeamworkGuy2
	 * @since 2015-1-17
	 */
	public static class PrimitiveInfos extends ClassTemplate {
		public List<IteratorInfos> iteratorTypes;
	}




	public static class IteratorInfos extends ClassTemplate {
		public List<PrimitiveIteratorInfo> types;
	}




	public static class WrapperTemplateInfo extends PrimitiveTypeClassTemplate {
		public String iteratorName;

		public WrapperTemplateInfo(Class<?> primitiveType) {
			super(primitiveType);
			this.iteratorName = this.typeShortTitleCase + "Iterator";
		}

	}




	public static class MapTmplInfo extends PrimitiveTypeClassTemplate {
		public String valueType;
		public String defaultValueValue;

		public MapTmplInfo(Class<?> keyType, Class<?> valueType, String defaultValueValue) {
			super(keyType);
			this.valueType = JPrimitiveType.fromType(valueType).getJavaPrimitiveName();
			this.defaultValueValue = defaultValueValue;
		}

	}




	public static class ArrayViewInfo extends WrapperTemplateInfo {
		public String collectionType;
		public String iteratorImplName;
		public String iteratorWrapperName;
		public String compareValues;
		public boolean isPrimitive;

		public ArrayViewInfo(Class<?> primitiveType, String compareValues, boolean isPrimitive) {
			super(primitiveType);
			this.collectionType = this.typeShortTitleCase + "ArrayList";
			this.compareValues = compareValues;
			this.isPrimitive = isPrimitive;
			this.implementClassNames = Arrays.asList(this.typeShortTitleCase + "List");
			this.iteratorImplName = this.typeShortTitleCase + "ArrayIterator";
			this.iteratorWrapperName = this.typeShortTitleCase + "IteratorWrapper";
		}

	}




	/**
	 * @author TeamworkGuy2
	 * @since 2015-1-17
	 */
	public static class PrimitiveIteratorInfo extends PrimitiveTypeClassTemplate {
		public String collectionType;
		public String sizeGetter;
		public String getterStart;
		public String getterEnd;
		public String modGetter;
		public boolean hasOwnMod;
		public boolean hasCollMod;

		public PrimitiveIteratorInfo() {
		}

		public PrimitiveIteratorInfo(Class<?> primitiveType) {
			super(primitiveType);
		}

		public PrimitiveIteratorInfo(Class<?> primitiveType, String collectionType) {
			super(primitiveType);
			this.collectionType = TemplateNames.inferClassName(collectionType, JPrimitiveType.fromType(primitiveType));
		}

	}




	public static final void generatePrimitiveCollections() throws IOException {
		generatePrimitiveListReadOnly();
		generatePrimitiveSearchable();
		generatePrimitiveList();
		generatePrimitiveArrayList();
		generatePrimitiveSortedList();
		generatePrimitiveBag();
		generatePrimitiveViews();
		generatePrimitiveMapsReadOnly();
		generatePrimitiveMaps();
		generatePrimitiveViewHandles();
		generatePrimitiveIteratorsReadOnly();
		generatePrimitiveIterators();
		generatePrimitiveIteratorImpls();
		generatePrimitiveIteratorWrappers();
	}


	public static void main(String[] args) throws IOException {
		generatePrimitiveCollections();
	}

}
