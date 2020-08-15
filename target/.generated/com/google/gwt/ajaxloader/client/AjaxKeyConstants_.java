package com.google.gwt.ajaxloader.client;

public class AjaxKeyConstants_ implements com.google.gwt.ajaxloader.client.AjaxKeyConstants {
  
  public java.util.Map<java.lang.String, java.lang.String> ajaxKeys() {
    java.util.Map<java.lang.String, java.lang.String> args = (java.util.Map<java.lang.String, java.lang.String>) cache.get("ajaxKeys");
    if (args == null) {
      args = new java.util.LinkedHashMap<String, String>();
      args.put("localhost:8080", "ABQIAAAAG8LzhtshQCjpSshU_uJjmxTwM0brOpm-All5BF6PoaKBxRWWERTZER2lJ4GnsG8nvhKLOQ20degaEQ");
      args.put("127.0.0.1:8080", "ABQIAAAAG8LzhtshQCjpSshU_uJjmxTBfUk9TZrBRaIteybtnU2KziHEpRQvhPNTjo7DMczjrRD3yBPRJ_BSQQ");
      args = java.util.Collections.unmodifiableMap(args);
      cache.put("ajaxKeys", args);
    }
    return args;
  }
  java.util.Map cache = new java.util.HashMap();
}
