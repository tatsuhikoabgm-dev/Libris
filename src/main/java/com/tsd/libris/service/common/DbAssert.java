package com.tsd.libris.service.common;

import com.tsd.libris.exception.ApplicationException;

public class DbAssert {
	
	   public static void assertSingleUpdate(int count, String operation) {
	        if (count == 0) {
	            throw new ApplicationException(
	                ApplicationException.Type.INVALID_REQUEST,
	                operation + " : 更新対象が存在しません"
	            );
	        }
	        if (count > 1) {
	            throw new ApplicationException(
	                ApplicationException.Type.SYSTEM,
	                operation + " : 複数行更新が発生しました (" + count + ")"
	            );
	        }
	    }

}
