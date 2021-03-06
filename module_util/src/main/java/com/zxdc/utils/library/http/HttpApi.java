package com.zxdc.utils.library.http;


import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.CheckCode;
import com.zxdc.utils.library.bean.ConstractDetails;
import com.zxdc.utils.library.bean.Contract;
import com.zxdc.utils.library.bean.ContractCode;
import com.zxdc.utils.library.bean.CustomerDetails;
import com.zxdc.utils.library.bean.CustomerList;
import com.zxdc.utils.library.bean.CustomerState;
import com.zxdc.utils.library.bean.Department;
import com.zxdc.utils.library.bean.Dept;
import com.zxdc.utils.library.bean.Device;
import com.zxdc.utils.library.bean.DeviceDetails;
import com.zxdc.utils.library.bean.DeviceType;
import com.zxdc.utils.library.bean.Dict;
import com.zxdc.utils.library.bean.EntryBonus;
import com.zxdc.utils.library.bean.EntryBonusDetails;
import com.zxdc.utils.library.bean.Financial;
import com.zxdc.utils.library.bean.FinancialDetails;
import com.zxdc.utils.library.bean.Income;
import com.zxdc.utils.library.bean.Inventory;
import com.zxdc.utils.library.bean.InventoryDetails;
import com.zxdc.utils.library.bean.LedTable;
import com.zxdc.utils.library.bean.LedTableDetails;
import com.zxdc.utils.library.bean.Log;
import com.zxdc.utils.library.bean.LogDetails;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.MaterialInventory;
import com.zxdc.utils.library.bean.Notice;
import com.zxdc.utils.library.bean.Office;
import com.zxdc.utils.library.bean.OutAndEntry;
import com.zxdc.utils.library.bean.OutBound;
import com.zxdc.utils.library.bean.OutBoundDetails;
import com.zxdc.utils.library.bean.OutBoundProduct;
import com.zxdc.utils.library.bean.PlanDetails;
import com.zxdc.utils.library.bean.Procurement;
import com.zxdc.utils.library.bean.ProcurementDetails;
import com.zxdc.utils.library.bean.ProductPlan;
import com.zxdc.utils.library.bean.ProductProgress;
import com.zxdc.utils.library.bean.SalesWage;
import com.zxdc.utils.library.bean.SalesWageDetails;
import com.zxdc.utils.library.bean.SdEnter;
import com.zxdc.utils.library.bean.SdEnterDetails;
import com.zxdc.utils.library.bean.SelectCustomer;
import com.zxdc.utils.library.bean.SellingDetails;
import com.zxdc.utils.library.bean.SellingOutBound;
import com.zxdc.utils.library.bean.StatisticalGoods;
import com.zxdc.utils.library.bean.StatisticalMaterial;
import com.zxdc.utils.library.bean.StatisticalSales;
import com.zxdc.utils.library.bean.StockList;
import com.zxdc.utils.library.bean.Supplier;
import com.zxdc.utils.library.bean.SupplierDetails;
import com.zxdc.utils.library.bean.SupplierMaterial;
import com.zxdc.utils.library.bean.SupplierName;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.UserList;
import com.zxdc.utils.library.bean.Wage;
import com.zxdc.utils.library.bean.WorkerDetails;
import com.zxdc.utils.library.bean.parameter.AddContractP;
import com.zxdc.utils.library.bean.parameter.AddCustomerP;
import com.zxdc.utils.library.bean.parameter.AddDeviceP;
import com.zxdc.utils.library.bean.parameter.AddFinancialP;
import com.zxdc.utils.library.bean.parameter.AddLedTableP;
import com.zxdc.utils.library.bean.parameter.AddLogP;
import com.zxdc.utils.library.bean.parameter.AddOutBoundByProductP;
import com.zxdc.utils.library.bean.parameter.AddPaymentP;
import com.zxdc.utils.library.bean.parameter.AddProcurementP;
import com.zxdc.utils.library.bean.parameter.AddProductPlanP;
import com.zxdc.utils.library.bean.parameter.AddPutStorageP;
import com.zxdc.utils.library.bean.parameter.AddSdEnterP;
import com.zxdc.utils.library.bean.parameter.AddSupplierMaterialP;
import com.zxdc.utils.library.bean.parameter.AddSupplierP;
import com.zxdc.utils.library.bean.parameter.AuditOutBoundP;
import com.zxdc.utils.library.bean.parameter.CustomerAuditP;
import com.zxdc.utils.library.bean.parameter.EditSupplierGoodsP;
import com.zxdc.utils.library.bean.parameter.LoginP;
import com.zxdc.utils.library.bean.parameter.OutBoundP;
import com.zxdc.utils.library.bean.parameter.SalesOutBoundP;
import com.zxdc.utils.library.bean.parameter.UpdateCustomerStateP;
import com.zxdc.utils.library.bean.parameter.UpdateEntryGoodP;
import com.zxdc.utils.library.bean.parameter.UpdateFinancial;
import com.zxdc.utils.library.bean.parameter.UpdateProductP;
import com.zxdc.utils.library.bean.parameter.UpdatePwdP;
import com.zxdc.utils.library.bean.parameter.UpdateWasteP;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface HttpApi {

    @POST(HttpConstant.LOGIN)
    Call<UserInfo> login(@Body LoginP loginP);

    @GET(HttpConstant.GET_DEPARTMENT)
    Call<Department> getDepartment();

    @PUT(HttpConstant.ADD_DEVICE)
    Call<BaseBean> addDevice(@Body AddDeviceP addDeviceP);

    @GET(HttpConstant.DEVICE_TYPE)
    Call<DeviceType> getDeviceType(@Query("pid") int pid);

    @GET(HttpConstant.GET_DEVICE_LIST)
    Call<Device> getDeviceList(@Query("initialism") String initialism, @Query("id") String id, @Query("page") String page,@Query("limit") int limit);

    @GET(HttpConstant.GET_DEVICE_DETAILS)
    Call<DeviceDetails> getDeviceDetails(@Query("id") int id);

    @GET(HttpConstant.GET_CUSTOMER_LIST)
    Call<SelectCustomer> getCustomerList(@Query("prop1") String prop1, @Query("userId") String userId);

    @GET(HttpConstant.GET_OFFICE)
    Call<Office> getOffice(@Query("deptId") int deptId);

    @PUT(HttpConstant.ADD_CONTRACT)
    Call<BaseBean> addContract(@Body AddContractP addContractP);

    @GET(HttpConstant.GET_CONTRACT_LIST)
    Call<Contract> getContractList(@Query("prop2") String prop2,@Query("customerId") String customerId, @Query("page") String page, @Query("limit") int limit);

    @GET(HttpConstant.GET_CONTRACT_DETAILS)
    Call<ConstractDetails> getConstractDetails(@Query("id") String id);

    @POST(HttpConstant.EDIT_CONTRACT)
    Call<BaseBean> editContract(@Body AddContractP addContractP);

    @GET(HttpConstant.CHECK_CODE)
    Call<CheckCode> checkCode(@Query("prop2") String prop2);

    @GET(HttpConstant.GET_INVENTROY_LIST)
    Call<Inventory> getInventoryList(@Query("goodsId") String goodsId,@Query("warnFlag") String warnFlag, @Query("page") int page, @Query("limit") int limit);

    @DELETE(HttpConstant.DELETE_FILE)
    Call<BaseBean> deleteFile(@Query("id") String id);

    @GET(HttpConstant.GET_MATERIAL_BY_NAME)
    Call<Material> getMeterialByName(@Query("prop3") String prop3,@Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_CONTRACT_CODE)
    Call<ContractCode> getContractCode(@Query("prop2") String prop2,@Query("page") int page, @Query("limit") int limit);

    @PUT(HttpConstant.ADD_OUTBOUND)
    Call<BaseBean> addOutBound(@Body OutBoundP outBoundP);

    @GET(HttpConstant.GET_OUTBOUND_LIST)
    Call<OutBound> getOutBoundList(@Query("startDate") String startDate,@Query("endDate") String endDate,@Query("customerId") String customerId, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_OUTBOUND_LIST)
    Call<OutBound> getOutBoundListByAudit(@Query("stateStr") String stateStr, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_OUTBOUND_DETAILS)
    Call<OutBoundDetails> getOutBoundDetails(@Query("id") int id);

    @PUT(HttpConstant.ADD_PLAN)
    Call<BaseBean> addPlan(@Body AddProductPlanP addProductPlanP);

    @GET(HttpConstant.GET_PLAN_LIST)
    Call<ProductPlan> getPlanList(@Query("planCode") String planCode,@Query("status")String status, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_PLAN_LIST)
    Call<ProductPlan> getAuditPlan(@Query("statusStr") String statusStr,@Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_PLAN_DETAILS)
    Call<PlanDetails> getPlanDetails(@Query("planId") int planId, @Query("deptId") int deptId);

    @PUT(HttpConstant.ADD_LOG)
    Call<BaseBean> addLog(@Body AddLogP addLogP);

    @GET(HttpConstant.GET_LOG_LIST)
    Call<Log> getLogList(@Query("customerId") String customerId, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_LOG_DETAILS)
    Call<LogDetails> getLogDetails(@Query("id") int id);

    @GET(HttpConstant.GET_DICT)
    Call<Dict> getDict(@Query("pid") int pid);

    @PUT(HttpConstant.ADD_CUSTOMER)
    Call<BaseBean> addCustomer(@Body AddCustomerP addCustomerP);

    @GET(HttpConstant.GET_CUSTOMER)
    Call<CustomerList> getCustomer(@Query("stateStr") String stateStr,@Query("privateState") String privateState,@Query("privateId")String privateId,@Query("contacts") String contacts, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_CUSTOMER_DETAILS)
    Call<CustomerDetails> getCustomerDetails(@Query("id") int id);

    @POST(HttpConstant.UPDATE_CUSTOMER_STATE)
    Call<BaseBean> updateCustomerState(@Body UpdateCustomerStateP updateCustomerStateP);

    @POST(HttpConstant.UPDATE_CUSTOMER)
    Call<BaseBean> updateCustomer(@Body AddCustomerP addCustomerP);

    @GET(HttpConstant.CHECK_CUSTOMER_NAME)
    Call<CheckCode> checkCustomerName(@Query("customerName") String customerName);

    @GET(HttpConstant.GET_USER_LIST)
    Call<UserList> getUserList(@Query("deptId") String deptId, @Query("name") String name, @Query("page") int page, @Query("limit") int limit);

    @PUT(HttpConstant.ADD_FINANCIAL)
    Call<BaseBean> addFinancial(@Body AddFinancialP addFinancialP);

    @GET(HttpConstant.GET_FINANCIAL_LIST)
    Call<Financial> getFinancialList(@Query("state") String state, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_FINANCIAL_DETAILS)
    Call<FinancialDetails> getFinancialDetails(@Query("id") int id);

    @GET(HttpConstant.GET_INVENTORY_DETAILS)
    Call<InventoryDetails> getInventoryDetails(@Query("goodsId") String goodsId,@Query("type") String type,@Query("stockType") String stockType,@Query("page") int page, @Query("limit") int limit);

    @PUT(HttpConstant.ADD_SD_ENTER)
    Call<BaseBean> addSdEnter(@Body AddSdEnterP addSdEnterP);

    @GET(HttpConstant.GET_SD_ENTER_LIST)
    Call<SdEnter> getSdEnterList(@Query("startPurcDate") String startPurcDate,@Query("endPurcDate") String endPurcDate,@Query("startCreateDate") String startCreateDate,@Query("endCreateDate") String endCreateDate, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_SD_ENTER_DETAILS)
    Call<SdEnterDetails> getSdEnterDetails(@Query("id") int id);

    @GET(HttpConstant.GET_MATERIAL_INVENTORY)
    Call<MaterialInventory> getMaterialInventory(@Query("prop3") String prop3);

    @PUT(HttpConstant.ADD_OUTBOUND_BYPRODUCT)
    Call<BaseBean> addOutBoundByProduct(@Body AddOutBoundByProductP addOutBoundByProductP);

    @GET(HttpConstant.GET_PRODUCT_PROGRESS)
    Call<ProductProgress> getProductProgress(@Query("requireId") int requireId, @Query("deptId") int deptId);

    @POST(HttpConstant.UPDATE_PRODUCT_STATUS)
    Call<BaseBean> updateProductStatus(@Body UpdateProductP updateProductP);

    @GET(HttpConstant.GET_DEPT_LIST)
    Call<Dept> getDeptList(@Query("name") String name, @Query("parentId") String parentId);

    @PUT(HttpConstant.ADD_PUT_STORAGE)
    Call<BaseBean> addPutStorage(@Body AddPutStorageP addPutStorageP);

    @GET(HttpConstant.GET_OUTBOUND_PRODUCT_LIST)
    Call<OutBoundProduct> getOutBoundProductList(@Query("planId") String planId, @Query("deptId")String deptId,@Query("outStatus")String outStatus,@Query("entryStatus")String entryStatus,@Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_CUSTOMER_FOLLOW)
    Call<Log> getFollow(@Query("customerId") int customerId, @Query("page") int page, @Query("limit") int limit);

    @POST(HttpConstant.AUDIT_OUTBOUND)
    Call<BaseBean> AuditOutBound(@Body AuditOutBoundP auditOutBoundP);

    @POST(HttpConstant.AUDIT_PLAN)
    Call<BaseBean> AuditPlan(@Body AuditOutBoundP auditOutBoundP);

    @GET(HttpConstant.GET_PROCUREMENT_LIST)
    Call<Procurement> getAuditProcurementList(@Query("stateStr") String stateStr, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_PROCUREMENT_LIST)
    Call<Procurement> getProcurementList(@Query("startDate") String startDate,@Query("endDate") String endDate,@Query("purcOrder") String purcOrder,@Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_PROCUREMENT_DETAILS)
    Call<ProcurementDetails> getProcurementDetails(@Query("id") int id);

    @GET(HttpConstant.GET_SUPPLIER_BY_NAME)
    Call<SupplierName> getSupplierNameByName(@Query("prop1") String prop1);

    @PUT(HttpConstant.ADD_PROCUREMENT)
    Call<BaseBean> AddProcurement(@Body AddProcurementP addProcurementP);

    @POST(HttpConstant.AUDIT_PROCUREMENT)
    Call<BaseBean> AuditProcurement(@Body AuditOutBoundP auditOutBoundP);

    @GET(HttpConstant.GET_FINANCIAL_LIST)
    Call<Financial> getAuditFinancialList(@Query("stateStr") String stateStr, @Query("page") int page, @Query("limit") int limit);

    @POST(HttpConstant.AUDIT_FINANCIAL)
    Call<BaseBean> AuditFinancial(@Body AuditOutBoundP auditOutBoundP);

    @GET(HttpConstant.GET_INCOME)
    Call<Income> getIncome(@Query("startDate") String startDate,@Query("endDate") String endDate);

    @GET(HttpConstant.GET_CUSTOMER_STATE)
    Call<CustomerState> getCustomerState();

    @GET(HttpConstant.GET_STATISTICAL_SALES)
    Call<StatisticalSales> getStatistionSales(@Query("endDate") String endDate);

    @GET(HttpConstant.GET_STATISTICAL_MATERIAL)
    Call<StatisticalMaterial> getStatisticalMaterial(@Query("endDate") String endDate);

    @GET(HttpConstant.GET_STATISTICAL_GOODS)
    Call<StatisticalGoods> getStatisticalGoods();

    @GET(HttpConstant.IP+"supplier/checkNameIsUniq")
    Call<BaseBean> checkSupplierName(@Query("supplierName") String supplierName);

    @PUT(HttpConstant.IP+"supplier/save")
    Call<BaseBean> addSupplier(@Body AddSupplierP addSupplierP);

    @POST(HttpConstant.IP+"supplier/update")
    Call<BaseBean> UpdateSupplier(@Body AddSupplierP addSupplierP);

    @GET(HttpConstant.IP+"supplier/getList")
    Call<Supplier> getSupplierList(@Query("id") String id, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.IP+"supplier/getDetailById")
    Call<SupplierDetails> getSupplierDetails(@Query("id") int id);

    @POST(HttpConstant.IP+"supplier/updateDetail")
    Call<BaseBean> editSupplierPrice(@Body EditSupplierGoodsP editSupplierGoods);

    @DELETE(HttpConstant.IP+"supplier/deleteDetail")
    Call<BaseBean> deleteSupplierGoods(@Query("id") int id);

    @GET(HttpConstant.IP+"supplier/goodsListByName")
    Call<SupplierMaterial> getSupplierDetails(@Query("prop3") String prop3);

    @PUT(HttpConstant.IP+"supplier/saveDetailList")
    Call<BaseBean> AddSupplierMaterial(@Body AddSupplierMaterialP addSupplierMaterialP);

    @GET(HttpConstant.IP+"entryProductDetail/getWageListOfWorkers")
    Call<Wage> getWageList(@Query("createId") String createId,@Query("deptId") String deptId,@Query("month") String month);

    @POST(HttpConstant.IP+"purchase/update")
    Call<BaseBean> EditProcurement(@Body AddProcurementP addProcurementP);

    @POST(HttpConstant.IP+"outOrder/update")
    Call<BaseBean> updateOutOrder(@Body SalesOutBoundP salesOutBoundP);

    @POST(HttpConstant.IP+"reimFinance/update")
    Call<BaseBean> updateFinancial(@Body UpdateFinancial transferP);

    @POST(HttpConstant.IP+"equipment/update")
    Call<BaseBean> updateDevice(@Body AddDeviceP addDeviceP);

    @GET(HttpConstant.IP+"outPartRequire/list")
    Call<LedTable> getLedTable(@Query("startDate") String startDate,@Query("endDate") String endDate,@Query("deptId") String deptId, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_CUSTOMER)
    Call<CustomerList> getCustomerByStatistical(@Query("privateState") int privateState);

    @PUT(HttpConstant.IP+"outPartRequire/save")
    Call<BaseBean> addLedTable(@Body AddLedTableP addLedTable);

    @GET(HttpConstant.IP+"outPartRequire/detail")
    Call<LedTableDetails> getLedTableDetails(@Query("id") int id);

    @POST(HttpConstant.IP+"entryProductDetail/update")
    Call<BaseBean> updateEntryGood(@Body UpdateEntryGoodP updateEntryGoodP);

    @POST(HttpConstant.IP+"oddsRejectDetail/update")
    Call<BaseBean> updateWaste(@Body UpdateWasteP updateWasteP);

    @GET(HttpConstant.IP+"notice/list")
    Call<Notice> getNoticeList(@Query("type") int type, @Query("page") int page, @Query("limit") int limit);

    @POST(HttpConstant.IP+"customer/approval")
    Call<BaseBean> auditCustomer(@Body CustomerAuditP customerAuditP);

    @GET(HttpConstant.IP+"selling/getList")
    Call<SellingOutBound> getSellingList(@Query("stateStr") String stateStr,@Query("startDate") String startDate, @Query("endDate") String endDate, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.IP+"selling/getDetail")
    Call<SellingDetails> getSellingDetails(@Query("id") int id);

    @POST(HttpConstant.IP+"user/modifyPassword")
    Call<BaseBean> updatePwd(@Body UpdatePwdP updatePwdP);

    @GET(HttpConstant.IP+"stockType/list")
    Call<StockList> getStockList();

    @GET(HttpConstant.IP+"requireDetail/listByBatchNoAndDeptAndCreator")
    Call<OutAndEntry> getProductOutList(@Query("outStatus") int outStatus,@Query("deptId") String deptId, @Query("createId") String createId,@Query("startDate") String startDate, @Query("endDate") String endDate, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.IP+"entryProductDetail/listByBatchNoAndDeptAndCreator")
    Call<OutAndEntry> getProductEntryList(@Query("entryStatus") int entryStatus,@Query("deptId") String deptId, @Query("createId") String createId,@Query("startDate") String startDate, @Query("endDate") String endDate, @Query("page") int page, @Query("limit") int limit);

    @PUT(HttpConstant.IP+"contractDetail/save")
    Call<BaseBean> addPayMent(@Body AddPaymentP addPaymentP);

    @GET(HttpConstant.IP+"entryProductDetail/list")
    Call<WorkerDetails> getWorkerDetails(@Query("month") String month,@Query("createId") int createId, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.IP+"contractDetail/getSalesIncome")
    Call<SalesWage> getSalesWage(@Query("salesId") String salesId,@Query("month") String month);

    @GET(HttpConstant.IP+"contractDetail/list")
    Call<SalesWageDetails> getSalesWageDetails( @Query("salesId") int salesId,@Query("month") String month, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.IP+"customer/getIncomeForSaveCustomer")
    Call<EntryBonus> getEntryBonus(@Query("createId") String createId, @Query("month") String month);

    @GET(HttpConstant.IP+"customer/getIncomeDetailForSaveCustomer")
    Call<EntryBonusDetails> getEntryBonusDetails(@Query("createId") int createId, @Query("month") String month, @Query("page") int page, @Query("limit") int limit);

    @POST(HttpConstant.IP+"selling/approval")
    Call<BaseBean> AuditSelling(@Body AuditOutBoundP auditOutBoundP);


}
