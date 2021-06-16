package base.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity {

    @Id
    private Long id;
}
