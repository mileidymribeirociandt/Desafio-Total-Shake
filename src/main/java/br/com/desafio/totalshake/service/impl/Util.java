package br.com.desafio.totalshake.service.impl;

import br.com.desafio.totalshake.controller.dto.ItemPedidoDTO;
import br.com.desafio.totalshake.service.exceptions.InvalidIdException;

import java.time.LocalDateTime;
import java.util.List;

final class Util {
    public static boolean isNegative(Long number){
        return number < 0;
    }

    public static boolean isZero(Long number){
        return number == 0;
    }

    public static boolean isNegative(Integer number){
        return number < 0;
    }

    public static boolean isZero(Integer number){
        return number == 0;
    }


    public static boolean isEmptyOrNullItemList(List<ItemPedidoDTO> itemPedidoDTOList){
        return itemPedidoDTOList == null || itemPedidoDTOList.isEmpty();
    }

    public static boolean isDateTimeInFuture(LocalDateTime localDateTimeToValidate){
        return localDateTimeToValidate.compareTo(LocalDateTime.now()) > 0;
    }

    public static void verifyId(Long id){
        if(isNegative(id)){
            throw new InvalidIdException("Id can't be a negative number!");
        }

        if(isZero(id)){
            throw new InvalidIdException("Id can't be zero!");
        }
    }


}
