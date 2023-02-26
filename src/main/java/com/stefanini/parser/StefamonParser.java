package com.stefanini.parser;

import com.stefanini.dto.StefamonDTO;
import com.stefanini.entity.Stefamon;

public class StefamonParser {

    public static Stefamon dtoToEntity(StefamonDTO dto) {
        Stefamon stefamon = new Stefamon(dto);
        return stefamon;
    }

    public static StefamonDTO entityToDTO(Stefamon stefamon) {
        return new StefamonDTO(stefamon);
    }

}
