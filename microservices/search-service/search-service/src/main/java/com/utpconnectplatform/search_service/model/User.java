package com.utpconnectplatform.search_service.model;

import lombok.*;

@Getter
@Setter
public class User {
    private long id_usersp;
    private String bio_text;
    private String studies;
    private String birthday;
    private String profile_img_url;
    private String cover_img_url;
}
