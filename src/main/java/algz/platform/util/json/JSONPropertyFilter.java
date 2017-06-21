/**
 * @author algz
 *
 */
package algz.platform.util.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author RenYiwei
 *
 */
@Retention(RetentionPolicy.RUNTIME)
//可以注解在字段，方法，类上
@Target({ElementType.FIELD, ElementType.METHOD,ElementType.TYPE })
public @interface JSONPropertyFilter {

//默认注解了就是过滤
boolean Value() default true;
}