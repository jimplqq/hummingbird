package models.test;

import io.ebean.annotation.DbComment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import models.BaseModel;

import javax.persistence.Entity;

/**
 * @author qingyun
 * @date 2021/5/12 2:18 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Test extends BaseModel {

  @DbComment("名称")
  private String name;
}
