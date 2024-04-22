package io.github.krzyzyb.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HexagonalContentGenerator {
  static List<String> prefixes = Arrays.asList("P1", "P3", "C1", "C2", "B1", "B2", "U1", "U2");

  public static List<String> generateHexadecimal() {
    List<String> result = new ArrayList<>();

    prefixes.forEach(prefix ->{
      if (prefix.equals("P3")) {
        for (int i = 0; i <= 0x3FF; i++) {
          String hex = Integer.toHexString(i).toUpperCase();
          result.add(modifyString(prefix, hex));
        }
      }else{
        for (int i = 0; i <= 0xFFF; i++) {
          String hex = Integer.toHexString(i).toUpperCase();
          result.add(modifyString(prefix, hex));
        }
      }
    });
    return result;
  }


  static private String modifyString(String prefix, String value){
    String ONE = "0";
    String TWO = "00";
    if (value.length() == 1){
      return "'"+prefix+TWO+value+"'";
    }
    if (value.length() == 2){
      return "'"+prefix+ONE+value+"'";
    }
    if (value.length() == 3){
      return "'"+prefix+value+"'";
    }else{
      return "BULLSHIT";
    }
  }
}
