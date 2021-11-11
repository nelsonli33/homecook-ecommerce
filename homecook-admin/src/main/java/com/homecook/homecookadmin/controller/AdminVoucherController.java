package com.homecook.homecookadmin.controller;

import com.homecook.homecookadmin.controller.mapper.VoucherRestMapper;
import com.homecook.homecookadmin.dto.VoucherDTO;
import com.homecook.homecookadmin.facade.AdminVoucherFacade;
import com.homecook.homecookadmin.model.CreateVoucherRequest;
import com.homecook.homecookadmin.model.UpdateVoucherRequest;
import com.homecook.homecookadmin.model.Voucher;
import com.homecook.homecookadmin.util.validator.VoucherReqMsgValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminVoucherController
{
    private static final Logger log = LoggerFactory.getLogger(AdminVoucherController.class);

    private VoucherRestMapper voucherRestMapper;
    private VoucherReqMsgValidator voucherReqMsgValidator;
    private AdminVoucherFacade adminVoucherFacade;

    @Autowired
    public AdminVoucherController(
            VoucherRestMapper voucherRestMapper,
            VoucherReqMsgValidator voucherReqMsgValidator,
            AdminVoucherFacade adminVoucherFacade)
    {
        this.voucherRestMapper = voucherRestMapper;
        this.voucherReqMsgValidator = voucherReqMsgValidator;
        this.adminVoucherFacade = adminVoucherFacade;
    }

    @GetMapping(value = "/admin/api/v1/vouchers")
    public ResponseEntity<List<Voucher>> getVouchers()
    {
        final List<VoucherDTO> allVouchers = adminVoucherFacade.getAllVouchers();
        final List<Voucher> vouchers = voucherRestMapper.convertAllDTOtoResponse(allVouchers);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vouchers);
    }

    @GetMapping(value = "/admin/api/v1/vouchers/{voucherId}")
    public ResponseEntity<Voucher> getVoucherDetails(@PathVariable(value = "voucherId") Long voucherId)
    {
        final VoucherDTO voucherData = adminVoucherFacade.getVoucherDetails(voucherId);
        final Voucher voucher = voucherRestMapper.convertDTOtoResponse(voucherData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(voucher);
    }

    @PostMapping(value = "/admin/api/v1/vouchers")
    public ResponseEntity<Voucher> createVoucher(@RequestBody CreateVoucherRequest request, BindingResult errors)
    {
        voucherReqMsgValidator.validateCreateVoucherRequest(request, errors);
        final VoucherDTO voucherDTO = voucherRestMapper.convertRequestToDTO(request);
        final VoucherDTO voucherData = adminVoucherFacade.createVoucher(voucherDTO);
        final Voucher voucher = voucherRestMapper.convertDTOtoResponse(voucherData);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(voucher);
    }

    @PutMapping(value = "/admin/api/v1/vouchers/{voucherId}")
    public ResponseEntity<Voucher> updateVoucher(@RequestBody UpdateVoucherRequest request, BindingResult errors)
    {
        voucherReqMsgValidator.validateUpdateVoucherRequest(request, errors);
        final VoucherDTO voucherDTO = voucherRestMapper.convertRequestToDTO(request);
        final VoucherDTO voucherData = adminVoucherFacade.updateVoucher(voucherDTO);
        final Voucher voucher = voucherRestMapper.convertDTOtoResponse(voucherData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(voucher);
    }

    @DeleteMapping(value = "/admin/api/v1/vouchers/{voucherId}/scheduled")
    public ResponseEntity<?> deleteScheduledVoucher(@PathVariable(value = "voucherId") Long voucherId)
    {
        adminVoucherFacade.deleteScheduledVoucher(voucherId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping(value = "/admin/api/v1/vouchers/{voucherId}/end")
    public ResponseEntity<Voucher> endRunningVoucher(@PathVariable(value = "voucherId") Long voucherId)
    {
        final VoucherDTO voucherDTO = adminVoucherFacade.endVoucher(voucherId);
        final Voucher voucher = voucherRestMapper.convertDTOtoResponse(voucherDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(voucher);
    }
}
