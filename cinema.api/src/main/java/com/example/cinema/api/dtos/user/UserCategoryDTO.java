package com.example.cinema.api.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCategoryDTO {

    private boolean isStudent;
    private boolean isSenior;

}
