package com.carte.tresor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@AllArgsConstructor
@Getter
public class Coordonnees {
    private int x;
    private int y;
}
