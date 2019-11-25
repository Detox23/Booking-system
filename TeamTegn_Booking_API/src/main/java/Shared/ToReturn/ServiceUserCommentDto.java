package Shared.ToReturn;

import java.sql.Timestamp;

public class ServiceUserCommentDto {
    private Integer id;
    private Timestamp commentDate;
    private String commentText;
    private SystemUserDto systemUserByUserId;
}
