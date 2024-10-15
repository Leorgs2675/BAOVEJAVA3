package Entity;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sach {
    private String ma;
    private String ten;
    private Integer soTrang;
    private Integer donGia;
}
