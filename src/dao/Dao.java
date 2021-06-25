package dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectSql;

public class Dao {
private static final Dao dao = new Dao();
	
	public Dao() {}
	
	public static Dao instance() {
		
		return dao;
	}
	
	public int insert(Object obj) {	
		 
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] getter = new Method[fields.length];
		
		StringBuffer sql = new StringBuffer("insert into " + getTableName(obj.getClass()) + " (");
		String ls = "";
		int returnVal=-1;
		for(int i=0; i<fields.length; i++) {
			ls+="?,";
			String fieldName = fields[i].getName();
			sql.append(fieldName+",");
			try {
				getter[i] = obj.getClass().getMethod("get"+ fieldName.substring(0, 1).toUpperCase() 
														+ fieldName.substring(1));
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(")");
		sql.append(" values("+ls);
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(")");
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			ConnectSql con=new ConnectSql();
			pstmt = con.getConn().prepareStatement(sql.toString());
			for(int j=0; j<fields.length; j++) {
				pstmt.setObject(j + 1, getter[j].invoke(obj));
			}
			returnVal=pstmt.executeUpdate();
			con.close(pstmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal; 
	}
	
	public List<Object> select(Object obj) {		
		 
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] setter = new Method[fields.length];
		Class<?> c = obj.getClass();
		Constructor<?> constructor = null;
			
		StringBuffer sql = new StringBuffer("select ");
		List<Object> returnVal=null;
		for(int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			sql.append(fieldName+",");
			try {
				setter[i] = obj.getClass().getMethod("set"+ fieldName.substring(0, 1).toUpperCase() 
														+ fieldName.substring(1),fields[i].getType());
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" from " + getTableName(obj.getClass()));
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			ConnectSql con=new ConnectSql();
			constructor=c.getConstructor();
			pstmt = con.getConn().prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			returnVal = new ArrayList<Object>();
			while(rs!=null && rs.next()) {
				Object o = constructor.newInstance();
				for(int i=0; i<fields.length; i++) {
					//System.out.println(fields[i].getType().toString());
					if(fields[i].getType().toString().equals("class java.lang.Integer")) {
						setter[i].invoke(o,rs.getInt(fields[i].getName()));
						//System.out.println(rs.getInt(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.String")) {
						setter[i].invoke(o,rs.getString(fields[i].getName()));
						//System.out.println(rs.getString(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.Double")) {
						setter[i].invoke(o,rs.getDouble(fields[i].getName()));
						//System.out.println(rs.getDouble(fields[i].getName()));
					}
				}
				returnVal.add(o);
			}
			con.close(rs);
			con.close(pstmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}

	public int update(Object obj) {	
		 
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] getter = new Method[fields.length];
		
		
		StringBuffer sql = new StringBuffer("update " + getTableName(obj.getClass()) + " set");
		int returnVal=-1;
		for(int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			if(i>=1) {
				sql.append(" "+fieldName+"=?,");
			}
			try {
				getter[i] = obj.getClass().getMethod("get"+ fieldName.substring(0, 1).toUpperCase() 
														+ fieldName.substring(1));
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" where "+ fields[0].getName()+"=?");
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			ConnectSql con=new ConnectSql();
			pstmt = con.getConn().prepareStatement(sql.toString());
			for(int j=1; j<fields.length; j++) {
				pstmt.setObject(j, getter[j].invoke(obj));
			}
			pstmt.setObject(fields.length, getter[0].invoke(obj));
			returnVal=pstmt.executeUpdate();
			con.close(pstmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal; 
	}
	
	public int delete(Object obj) {	
		 
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] getter = new Method[fields.length];
		
		StringBuffer sql = new StringBuffer("delete from " + getTableName(obj.getClass()) + " where ");
		int returnVal=-1;
		for(int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			if(i==0) {
				sql.append(" "+fieldName+"=?");
			}
			try {
				getter[i] = obj.getClass().getMethod("get"+ fieldName.substring(0, 1).toUpperCase() 
														+ fieldName.substring(1));
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			ConnectSql con=new ConnectSql();
			pstmt = con.getConn().prepareStatement(sql.toString());
			pstmt.setObject(1, getter[0].invoke(obj));
			returnVal=pstmt.executeUpdate();
			con.close(pstmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal; 
	}
	
	public List<Object> search(Object obj) {
		 
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] setter = new Method[fields.length];
		Method[] getter = new Method[fields.length];
		boolean[] flag = new boolean[fields.length];
		Class<?> c = obj.getClass();
		Constructor<?> constructor = null;
			
		StringBuffer sql = new StringBuffer("select ");
		List<Object> returnVal=null;
		String ls = "";
		for(int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			sql.append(fieldName+",");
			try {
				setter[i] = obj.getClass().getMethod("set"+ fieldName.substring(0, 1).toUpperCase() 
														+ fieldName.substring(1),fields[i].getType());
				getter[i] = obj.getClass().getMethod("get"+ fieldName.substring(0, 1).toUpperCase() 
						+ fieldName.substring(1));
				if(getter[i].invoke(obj)!=null) {
					if(ls.length()!=0) {
						ls+=" and ";
					}
					ls += fieldName+" Like '%"+getter[i].invoke(obj)+"%' ";
					flag[i]=true;
				}else {
					flag[i]=false;
				}
				System.out.println(getter[i].invoke(obj));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" from " + getTableName(obj.getClass())+" ");
		sql.append(" where "+ ls);
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			ConnectSql con=new ConnectSql();
			constructor=c.getConstructor();
			pstmt = con.getConn().prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			returnVal = new ArrayList<Object>();
			while(rs!=null && rs.next()) {
				Object o = constructor.newInstance();
				for(int i=0; i<fields.length; i++) {
					//System.out.println(fields[i].getType().toString());
					if(fields[i].getType().toString().equals("class java.lang.Integer")) {
						setter[i].invoke(o,rs.getInt(fields[i].getName()));
						//System.out.println(rs.getInt(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.String")) {
						setter[i].invoke(o,rs.getString(fields[i].getName()));
						//System.out.println(rs.getString(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.Double")) {
						setter[i].invoke(o,rs.getDouble(fields[i].getName()));
						//System.out.println(rs.getDouble(fields[i].getName()));
					}
				}
				returnVal.add(o);
			}
			con.close(rs);
			con.close(pstmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal;
		 
	}
	
	public List<Object> searchs(Object obj) {
		 
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] setter = new Method[fields.length];
		Method[] getter = new Method[fields.length];
		boolean[] flag = new boolean[fields.length];
		Class<?> c = obj.getClass();
		Constructor<?> constructor = null;
			
		StringBuffer sql = new StringBuffer("select ");
		List<Object> returnVal=null;
		String ls = "";
		for(int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			sql.append(fieldName+",");
			try {
				setter[i] = obj.getClass().getMethod("set"+ fieldName.substring(0, 1).toUpperCase() 
														+ fieldName.substring(1),fields[i].getType());
				getter[i] = obj.getClass().getMethod("get"+ fieldName.substring(0, 1).toUpperCase() 
						+ fieldName.substring(1));
				if(getter[i].invoke(obj)!=null) {
					if(ls.length()!=0) {
						ls+=" and ";
					}
					ls += fieldName+" Like "+getter[i].invoke(obj)+" ";
					flag[i]=true;
				}else {
					flag[i]=false;
				}
				System.out.println(getter[i].invoke(obj));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" from " + getTableName(obj.getClass())+" ");
		sql.append(" where "+ ls);
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			ConnectSql con=new ConnectSql();
			constructor=c.getConstructor();
			pstmt = con.getConn().prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			returnVal = new ArrayList<Object>();
			while(rs!=null && rs.next()) {
				Object o = constructor.newInstance();
				for(int i=0; i<fields.length; i++) {
					//System.out.println(fields[i].getType().toString());
					if(fields[i].getType().toString().equals("class java.lang.Integer")) {
						setter[i].invoke(o,rs.getInt(fields[i].getName()));
						//System.out.println(rs.getInt(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.String")) {
						setter[i].invoke(o,rs.getString(fields[i].getName()));
						//System.out.println(rs.getString(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.Double")) {
						setter[i].invoke(o,rs.getDouble(fields[i].getName()));
						//System.out.println(rs.getDouble(fields[i].getName()));
					}
				}
				returnVal.add(o);
			}
			con.close(rs);
			con.close(pstmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal;
		 
	}
	
	public List<Object> seacla(Object obj) {
		 
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] setter = new Method[fields.length];
		Method[] getter = new Method[fields.length];
		boolean[] flag = new boolean[fields.length];
		Class<?> c = obj.getClass();
		Constructor<?> constructor = null;
			
		StringBuffer sql = new StringBuffer("select DISTINCT ");
		List<Object> returnVal=null;
		String ls = "";
		for(int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			sql.append(fieldName+",");
			try {
				setter[i] = obj.getClass().getMethod("set"+ fieldName.substring(0, 1).toUpperCase() 
														+ fieldName.substring(1),fields[i].getType());
				getter[i] = obj.getClass().getMethod("get"+ fieldName.substring(0, 1).toUpperCase() 
						                                + fieldName.substring(1));
				if(getter[i].invoke(obj)!=null) {
					if(ls.length()!=0) {
						ls+=" and ";
					}
					ls += fieldName+" =? ";
					flag[i]=true;
				}else {
					flag[i]=false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" from Grade");
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			ConnectSql con=new ConnectSql();
			constructor=c.getConstructor();
			pstmt = con.getConn().prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			returnVal = new ArrayList<Object>();
			while(rs!=null && rs.next()) {
				Object o = constructor.newInstance();
				for(int i=0; i<fields.length; i++) {
					//System.out.println(fields[i].getType().toString());
					if(fields[i].getType().toString().equals("class java.lang.Integer")) {
						setter[i].invoke(o,rs.getInt(fields[i].getName()));
						//System.out.println(rs.getInt(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.String")) {
						setter[i].invoke(o,rs.getString(fields[i].getName()));
						//System.out.println(rs.getString(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.Double")) {
						setter[i].invoke(o,rs.getDouble(fields[i].getName()));
						//System.out.println(rs.getDouble(fields[i].getName()));
					}
				}
				returnVal.add(o);
			}
			con.close(rs);
			con.close(pstmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal;
		 
	}
	
	public List<Object> seasem(Object obj) {
		 
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] setter = new Method[fields.length];
		Method[] getter = new Method[fields.length];
		boolean[] flag = new boolean[fields.length];
		Class<?> c = obj.getClass();
		Constructor<?> constructor = null;
			
		StringBuffer sql = new StringBuffer("select DISTINCT ");
		List<Object> returnVal=null;
		String ls = "";
		for(int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			sql.append(fieldName+",");
			try {
				setter[i] = obj.getClass().getMethod("set"+ fieldName.substring(0, 1).toUpperCase() 
														+ fieldName.substring(1),fields[i].getType());
				getter[i] = obj.getClass().getMethod("get"+ fieldName.substring(0, 1).toUpperCase() 
						                                + fieldName.substring(1));
				if(getter[i].invoke(obj)!=null) {
					if(ls.length()!=0) {
						ls+=" and ";
					}
					ls += fieldName+" =? ";
					flag[i]=true;
				}else {
					flag[i]=false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" from course");
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			ConnectSql con=new ConnectSql();
			constructor=c.getConstructor();
			pstmt = con.getConn().prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			returnVal = new ArrayList<Object>();
			while(rs!=null && rs.next()) {
				Object o = constructor.newInstance();
				for(int i=0; i<fields.length; i++) {
					//System.out.println(fields[i].getType().toString());
					if(fields[i].getType().toString().equals("class java.lang.Integer")) {
						setter[i].invoke(o,rs.getInt(fields[i].getName()));
						//System.out.println(rs.getInt(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.String")) {
						setter[i].invoke(o,rs.getString(fields[i].getName()));
						//System.out.println(rs.getString(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.Double")) {
						setter[i].invoke(o,rs.getDouble(fields[i].getName()));
						//System.out.println(rs.getDouble(fields[i].getName()));
					}
				}
				returnVal.add(o);
			}
			con.close(rs);
			con.close(pstmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal;
		 
	}
	
	public Object find(Object obj) {
		 
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] setter = new Method[fields.length];
		Method[] getter = new Method[fields.length];
		boolean[] flag = new boolean[fields.length];
		Class<?> c = obj.getClass();
		Constructor<?> constructor = null;
		
		StringBuffer sql = new StringBuffer("select ");
		Object returnVal=null;
		String ls = "";
		for(int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			sql.append(fieldName+",");
			try {
				setter[i] = obj.getClass().getMethod("set"+ fieldName.substring(0, 1).toUpperCase() 
														+ fieldName.substring(1),fields[i].getType());
				getter[i] = obj.getClass().getMethod("get"+ fieldName.substring(0, 1).toUpperCase() 
						                                + fieldName.substring(1));
				if(getter[i].invoke(obj)!=null) {
					if(ls.length()!=0) {
						ls+=" and ";
					}
					ls += fieldName+" =? ";
					flag[i]=true;
				}else {
					flag[i]=false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" from " + getTableName(obj.getClass())+" ");
		sql.append(" where "+ ls);
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			ConnectSql con=new ConnectSql();
			pstmt = con.getConn().prepareStatement(sql.toString());
			constructor=c.getConstructor();
			int j=1;
			for(int i=0; i<fields.length; i++) {
				if(flag[i]==true) {
					pstmt.setObject(j++, getter[i].invoke(obj));
				}
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs!=null && rs.next()) {
				returnVal = constructor.newInstance();
				for(int i=0; i<fields.length; i++) {
					if(fields[i].getType().toString().equals("class java.lang.Integer")) {
						setter[i].invoke(returnVal,rs.getInt(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.String")) {
						setter[i].invoke(returnVal,rs.getString(fields[i].getName()));
					}else if(fields[i].getType().toString().equals("class java.lang.Double")) {
						setter[i].invoke(returnVal,rs.getDouble(fields[i].getName()));
					}
				}
			}
			con.close(rs);
			con.close(pstmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal; 
	}
	
    @SuppressWarnings("unused")
	private Object methodInvoke(Method method, Object obj, Object...args) {
		
		Object result = null;
		
		try {
			result = method.invoke(obj, args);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	@SuppressWarnings("rawtypes")
	public  String getTableName(Class clazz) {
		String[] name = clazz.getName().split("\\.");
		String tableName = name[name.length - 1];
		return tableName;
	}
	

}
