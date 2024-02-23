package com.app.expensetracker.service;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CatTransResponse {
    String cat_id=null;
    String user_id=null;
    List<Double> trans=null;


}
