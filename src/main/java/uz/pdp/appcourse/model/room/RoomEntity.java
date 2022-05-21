package uz.pdp.appcourse.model.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appcourse.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "room")
public class RoomEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;

    private float capacity;
}
