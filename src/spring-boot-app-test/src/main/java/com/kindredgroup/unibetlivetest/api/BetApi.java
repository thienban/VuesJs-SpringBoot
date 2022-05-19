package com.kindredgroup.unibetlivetest.api;

import com.kindredgroup.unibetlivetest.dto.BetDto;
import com.kindredgroup.unibetlivetest.dto.CustomerBetDto;
import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.mapper.CustomerBetMapper;
import com.kindredgroup.unibetlivetest.service.BetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(Urls.basePath)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BetApi {

    private final BetService betService;
    private final CustomerBetMapper customerBetMapper;

    @PostMapping(Urls.addBet)
    @ApiOperation(value = "Add the bet based on bet provided", response = BetDto.class)
    public void addBet(@RequestBody BetDto bet) {
        betService.addBet(bet);
    }

    @GetMapping(Urls.bets)
    @ApiOperation(value = "Get bets by customer pseudo", response = Bet.class)
    public List<CustomerBetDto> getBets() {
        return customerBetMapper.customerBetDto(betService.getBetByPseudo("unibest"));
    }

}
