/*
 * Created by babydeveloper on 1/16/21.
 *  Store Number	Name	Features - Products	Phone Number	Insert Date
 */

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Starbucks {

    private String name;
    private Integer storeNumber;
    private String features;
    private String phoneNumber;
    private String insertDate;
    private String owner;


    public Starbucks(String name, Integer storeNumber, String features,String phoneNumber, String insertDate ) {
        this.name = name;
        this.storeNumber = storeNumber;
        this.features = features;
        this.phoneNumber = phoneNumber;
        this.insertDate = insertDate;
    }
}
