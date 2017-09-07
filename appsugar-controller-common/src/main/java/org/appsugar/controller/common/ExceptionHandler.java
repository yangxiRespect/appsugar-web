package org.appsugar.controller.common;

/**
 * Controller layer Exception process
 * @author NewYoung
 *
 */
public interface ExceptionHandler {
	Object handle(Throwable ex);
}
