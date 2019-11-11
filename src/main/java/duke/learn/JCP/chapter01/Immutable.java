/**
 * 
 */
package duke.learn.JCP.chapter01;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(SOURCE)
@Target({ TYPE, METHOD, ANNOTATION_TYPE })
/**
 * @author Kazi
 *
 */
public @interface Immutable {

}
