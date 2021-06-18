package utils;

import java.util.HashMap;
import java.util.Map;

//Apply Singleton
public class ScenarioContext {

  //Prevent create instance
  private ScenarioContext() {
  }

  private static ScenarioContext _INSTANCE;
  private Map<String, Object> data = new HashMap<>();
  private String _tempKey;

  public static ScenarioContext currentContext() {
    if (_INSTANCE == null) {
      _INSTANCE = new ScenarioContext();
    }
    return _INSTANCE;
  }

  public ScenarioContext set(String key) {
    _tempKey = key;
    return _INSTANCE;
  }

  public void with(Object value) {
    _INSTANCE.data.put(_tempKey, value);
  }

  public Object get(String key) {
    return _INSTANCE.data.getOrDefault(key, null);
  }

  public <T> T get(String key, Class<T> clazz) {
    return clazz.cast(_INSTANCE.data.getOrDefault(key, null));
  }

  public void clear() {
    data = new HashMap<>();
  }
}

