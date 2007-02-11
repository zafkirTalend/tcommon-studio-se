package com.quantum.editors;



abstract class PartitionTypes {

	public final static String SQL_COMMENT = "__sql_comment"; //$NON-NLS-1$
	public final static String SQL_IDENTIFIER = "__sql_word"; //$NON-NLS-1$
	public final static String SQL_STRING = "__sql_string"; //$NON-NLS-1$
	public final static String SQL_KEYWORD = "__sql_keyword"; //$NON-NLS-1$
	public final static String SQL_SYMBOL = "__sql_symbol"; //$NON-NLS-1$
	public final static String SQL_SEPARATOR = "__sql_separator"; //$NON-NLS-1$
	public final static String SQL_NUMERIC = "__sql_numeric"; //$NON-NLS-1$

//	public static boolean isSQLPartition(ITypedRegion region) {
//		return region != null;// && 
////			(SQL_COMMENT.equals(region.getType()) ||
////					SQL_IDENTIFIER.equals(region.getType()) ||
////					SQL_STRING.equals(region.getType()) ||
////					SQL_KEYWORD.equals(region.getType()) ||
////					SQL_SYMBOL.equals(region.getType()) ||
////					SQL_SEPARATOR.equals(region.getType()) ||
////					SQL_NUMERIC.equals(region.getType()));
//	}
	
}
