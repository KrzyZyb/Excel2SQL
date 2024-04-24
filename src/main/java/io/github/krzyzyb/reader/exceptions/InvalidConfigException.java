package io.github.krzyzyb.reader.exceptions;

import java.io.Serial;

public class InvalidConfigException extends Exception {
  @Serial
  private static final long serialVersionUID = 5098607177785661128L;

  public InvalidConfigException(String message) {
    super(message);
  }
}
