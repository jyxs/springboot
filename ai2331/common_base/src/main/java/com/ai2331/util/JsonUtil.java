package com.ai2331.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

public class JsonUtil {
	public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String EMPTY_JSON = "{}";
	private static boolean indent = false;

	private static MappingJsonFactory jf = new MappingJsonFactory();

	static {
		ObjectMapper om = jf.getCodec();
		
		om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
       	om.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
       	
    	om.setAnnotationIntrospector(new JacksonAnnotationIntrospector(){
			private static final long serialVersionUID = -7777697503463875076L;

			@Override
            public Object findFilterId(Annotated a) {
				Object obj = super.findFilterId(a);

				if(obj != null) return obj;
				else return a.getAnnotated();
            }
        });
       	
    	om.setFilterProvider(new SimpleFilterProvider() {
			private static final long serialVersionUID = 5008024488265740400L;

			@Override
    	    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter)
    	    {
				if(valueToFilter==null) {
					return super.findPropertyFilter(filterId, valueToFilter);
				}
    	    	PropertyFilter pf = null;
    	    	//System.out.println(">>>>>>>>> findPropertyFilter: " + filterId + " - " + valueToFilter + "- " + pf);
    	    	return pf==null?super.findPropertyFilter(filterId, valueToFilter): pf;
    	    }
		}.setDefaultFilter(SimpleBeanPropertyFilter.serializeAll()));
       	
	}

	private JsonUtil() {
	}
	
	static JsonFactory getJsonFactory() {
		return jf;
	}
	
	//reading
	public static Map<String, Object> read(String str) throws IOException {
		str = StringUtils.isEmpty(str) ? "" : str;
		return read(new StringReader(str));
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> read(Reader in) throws IOException {
		JsonParser jp = jf.createParser(in);
		Map<String, Object> m = jp.readValueAs(Map.class);
		jp.close();
		return m;
	}

	public static <T> T read(String str, Class<T> c) throws IOException {
		return read(new StringReader(str), c);
	}

	public static <T> T read(Reader in, Class<T> c) throws IOException {
		JsonParser jp = jf.createParser(in);
		T t = jp.readValueAs(c);
		jp.close();
		return t;
	}

	public static <T> T read(String str, TypeReference<T> c) throws IOException {
		return read(new StringReader(str), c);
	}

	public static <T> T read(Reader in, TypeReference<T> c) throws IOException {
		JsonParser jp = jf.createParser(in);
		T t = jp.readValueAs(c);
		jp.close();
		return t;
	}
	
	public static <T> List<T> readList(String str, Class<T> c) throws IOException {
		if(StringUtils.isEmpty(str)) return Collections.emptyList();
		
		CollectionType javaType = jf.getCodec().getTypeFactory().constructCollectionType(List.class, c);
		return jf.getCodec().readValue(str, javaType);
	}

	public static <K,V> Map<K, V> readMap(String str, Class<K> k, Class<V> v) throws IOException {
		if(StringUtils.isEmpty(str)) return Collections.emptyMap();

		MapType javaType = jf.getCodec().getTypeFactory().constructMapType(Map.class, k, v);
		return jf.getCodec().readValue(str, javaType);
	}
	
	//writing
	public static void write(Writer out, Object object) throws IOException {
		JsonGenerator jg = jf.createGenerator(out);
		if(indent){
			jg.useDefaultPrettyPrinter();
		}
		if (object != null) {
			jg.writeObject(object);
		} else {
			jg.writeRaw(EMPTY_JSON);
		}
		jg.close();
	}

	public static String toJSON(Object object) {
		StringWriter out = new StringWriter();
		try {
			write(out, object);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return out.toString();
	}

	//setter
	public static void setIndent(boolean indent) {
		JsonUtil.indent = indent;
	}
}
