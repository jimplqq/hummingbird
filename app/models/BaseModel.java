package models;

import io.ebean.Model;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author qingyun
 * @date 2021/5/12 2:19 下午
 */
@Data
@MappedSuperclass
public class BaseModel extends Model {
  @Id private Long id;
}
