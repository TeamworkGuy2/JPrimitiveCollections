package twg2.collections.primitiveCollections.templates;

import java.util.ArrayList;
import java.util.List;

import codeTemplate.ClassInfo;
import codeTemplate.primitiveTemplate.PrimitiveTypeClassTemplate;

/**
 * @author TeamworkGuy2
 * @since 2014-12-24
 */
public class PrimitiveListInfo extends PrimitiveTypeClassTemplate {
	/** the class name of the iterator class this primitive list uses */
	public String iteratorName;
	/** the class name of the primitive iterator class this primitive list uses */
	public String iteratorPrimitiveName;


	public PrimitiveListInfo(Class<?> primitiveType) {
		super(primitiveType);
		this.extendClassName = this.typeShortTitleCase + "ArrayList";
		this.implementClassNames = new ArrayList<>();
		this.implementClassNames.add(this.typeShortTitleCase + "List");
	}


	public static PrimitiveListInfo iteratorTypes(Class<?> primitiveType, String iterator, String iteratorPrimitive) {
		PrimitiveListInfo info = new PrimitiveListInfo(primitiveType);
		info.iteratorName = iterator.replace("$type$", info.typeShortTitleCase);
		info.iteratorPrimitiveName = iteratorPrimitive.replace("$type$", info.typeShortTitleCase);
		return info;
	}


	public static final <T extends ClassInfo> T implementClasses(T classInfo, List<String> implementsClasses) {
		classInfo.setImplementClassNames(new ArrayList<>(implementsClasses));
		return classInfo;
	}

}
