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
import com.zxdc.utils.library.bean.DownLoad;
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
import com.zxdc.utils.library.bean.NetWorkCallBack;
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
import com.zxdc.utils.library.bean.Upload;
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
import com.zxdc.utils.library.http.base.BaseRequst;
import com.zxdc.utils.library.http.base.Http;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpMethod extends BaseRequst {

    public static int limit=20;


    /**
     * 登录
     */
    public static void login(LoginP loginP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).login(loginP).enqueue(new Callback<UserInfo>() {
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<UserInfo> call, Throwable t) {
                netWorkCallBack.onFail(t);
            }
        });
    }


    /**
     * 获取部门列表
     */
    public static void getDepartment(final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDepartment().enqueue(new Callback<Department>() {
            public void onResponse(Call<Department> call, Response<Department> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Department> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取设备类型
     */
    public static void getDeviceType(final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDeviceType(8).enqueue(new Callback<DeviceType>() {
            public void onResponse(Call<DeviceType> call, Response<DeviceType> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<DeviceType> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 添加设备
     */
    public static void addDevice(AddDeviceP addDeviceP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addDevice(addDeviceP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 上传文件
     */
    public static void uploadFile(String fileName,File file, final NetWorkCallBack netWorkCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("source","blr");
        map.put("key",fileName);
        Http.upLoadFile(HttpConstant.UPLOAD_FILE,"file", file, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                try {
                    String str = response.body().string();
                    LogUtils.e(str);
                    netWorkCallBack.onSuccess(SPUtil.gson.fromJson(str, Upload.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(e.getMessage());
            }
        });
    }


    /**
     * 上传文件
     */
    public static void uploadByFileAndTypeAndFid(String fileName,File file,String type,String fid, final NetWorkCallBack netWorkCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("source","blr");
        map.put("key",fileName);
        map.put("type",type);
        map.put("fid",fid);
        Http.upLoadFile(HttpConstant.UPLOAD_FILE_BY_OBJECT,"file", file, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                try {
                    String str = response.body().string();
                    netWorkCallBack.onSuccess(SPUtil.gson.fromJson(str, Upload.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(e.getMessage());
            }
        });
    }



    /**
     * 获取设备列表
     */
    public static void getDeviceList(String initialism,String id,String page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDeviceList(initialism,id,page,limit).enqueue(new Callback<Device>() {
            public void onResponse(Call<Device> call, Response<Device> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Device> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取设备详情
     */
    public static void getDeviceDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDeviceDetails(id).enqueue(new Callback<DeviceDetails>() {
            public void onResponse(Call<DeviceDetails> call, Response<DeviceDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<DeviceDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取客户列表
     */
    public static void getCustomerList(String prop1, String userId,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getCustomerList(prop1,userId).enqueue(new Callback<SelectCustomer>() {
            public void onResponse(Call<SelectCustomer> call, Response<SelectCustomer> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SelectCustomer> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取指派内勤
     */
    public static void getOffice(int deptId, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOffice(deptId).enqueue(new Callback<Office>() {
            public void onResponse(Call<Office> call, Response<Office> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Office> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 新增合同
     */
    public static void addContract(AddContractP addContractP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addContract(addContractP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取合同列表
     */
    public static void getContractList(String prop2,String customerId,String page, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getContractList(prop2,customerId,page,limit).enqueue(new Callback<Contract>() {
            public void onResponse(Call<Contract> call, Response<Contract> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Contract> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取合同详情
     */
    public static void getConstractDetails(String id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getConstractDetails(id).enqueue(new Callback<ConstractDetails>() {
            public void onResponse(Call<ConstractDetails> call, Response<ConstractDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ConstractDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 修改合同
     */
    public static void editContract(AddContractP addContractP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).editContract(addContractP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 校验合同编码唯一性
     */
    public static void checkCode(String prop2,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).checkCode(prop2).enqueue(new Callback<CheckCode>() {
            public void onResponse(Call<CheckCode> call, Response<CheckCode> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<CheckCode> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取库存明细列表
     */
    public static void getInventoryList(String goodsId,String warnFlag,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getInventoryList(goodsId,warnFlag,page,limit).enqueue(new Callback<Inventory>() {
            public void onResponse(Call<Inventory> call, Response<Inventory> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Inventory> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 删除文件
     */
    public static void deleteFile(String id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).deleteFile(id).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 物料列表-名称模糊查询
     */
    public static void getMeterialByName(String prop3,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getMeterialByName(prop3,page,limit).enqueue(new Callback<Material>() {
            public void onResponse(Call<Material> call, Response<Material> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Material> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }



    /**
     * 根据合同编码模糊匹配合同列表
     */
    public static void getContractCode(String prop2,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getContractCode(prop2,page,limit).enqueue(new Callback<ContractCode>() {
            public void onResponse(Call<ContractCode> call, Response<ContractCode> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ContractCode> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 增加出库单
     */
    public static void addOutBound(OutBoundP outBoundP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addOutBound(outBoundP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取出库单列表
     */
    public static void getOutBoundList(String startDate,String endDate, String customerId, int page, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOutBoundList(startDate,endDate,customerId,page,limit).enqueue(new Callback<OutBound>() {
            public void onResponse(Call<OutBound> call, Response<OutBound> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<OutBound> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 查询出库单详情
     */
    public static void getOutBoundDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOutBoundDetails(id).enqueue(new Callback<OutBoundDetails>() {
            public void onResponse(Call<OutBoundDetails> call, Response<OutBoundDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<OutBoundDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 添加生产计划
     */
    public static void addPlan(AddProductPlanP addProductPlanP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addPlan(addProductPlanP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取生产计划列表
     */
    public static void getPlanList(String planCode,String status,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getPlanList(planCode,status,page,limit).enqueue(new Callback<ProductPlan>() {
            public void onResponse(Call<ProductPlan> call, Response<ProductPlan> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ProductPlan> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取生产计划详情
     */
    public static void getPlanDetails(int planId, int deptId,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getPlanDetails(planId,deptId).enqueue(new Callback<PlanDetails>() {
            public void onResponse(Call<PlanDetails> call, Response<PlanDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<PlanDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 添加日志
     */
    public static void addLog(AddLogP addLogP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addLog(addLogP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取日志列表
     */
    public static void getLogList(String customerId,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getLogList(customerId,page,limit).enqueue(new Callback<Log>() {
            public void onResponse(Call<Log> call, Response<Log> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Log> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取日志详情
     */
    public static void getLogDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getLogDetails(id).enqueue(new Callback<LogDetails>() {
            public void onResponse(Call<LogDetails> call, Response<LogDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<LogDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取字典数据
     */
    public static void getDict(int pid,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDict(pid).enqueue(new Callback<Dict>() {
            public void onResponse(Call<Dict> call, Response<Dict> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Dict> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 增加客户
     */
    public static void addCustomer(AddCustomerP addCustomerP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addCustomer(addCustomerP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取客户列表
     */
    public static void getCustomer(String stateStr,String privateState,String privateId,String contacts,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getCustomer(stateStr,privateState,privateId,contacts,page,limit).enqueue(new Callback<CustomerList>() {
            public void onResponse(Call<CustomerList> call, Response<CustomerList> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<CustomerList> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取客户详情
     */
    public static void getCustomerDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getCustomerDetails(id).enqueue(new Callback<CustomerDetails>() {
            public void onResponse(Call<CustomerDetails> call, Response<CustomerDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<CustomerDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 客户信息-修改私有状态
     */
    public static void updateCustomerState(UpdateCustomerStateP updateCustomerStateP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).updateCustomerState(updateCustomerStateP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 修改客户信息
     */
    public static void updateCustomer(AddCustomerP addCustomerP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).updateCustomer(addCustomerP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 校验客户名称唯一性
     */
    public static void checkCustomerName(String customerName, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).checkCustomerName(customerName).enqueue(new Callback<CheckCode>() {
            public void onResponse(Call<CheckCode> call, Response<CheckCode> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<CheckCode> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取用户列表
     */
    public static void getUserList(String deptId,String name,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getUserList(deptId,name,page,limit).enqueue(new Callback<UserList>() {
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<UserList> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 添加财务报销
     */
    public static void addFinancial(AddFinancialP addFinancialP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addFinancial(addFinancialP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取财务列表
     */
    public static void getFinancialList(String state,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getFinancialList(state,page,limit).enqueue(new Callback<Financial>() {
            public void onResponse(Call<Financial> call, Response<Financial> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Financial> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取财务详情
     */
    public static void getFinancialDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getFinancialDetails(id).enqueue(new Callback<FinancialDetails>() {
            public void onResponse(Call<FinancialDetails> call, Response<FinancialDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<FinancialDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取库存明细详情
     */
    public static void getInventoryDetails(String goodsId,String type,String stockType,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getInventoryDetails(goodsId,type,stockType,page,limit).enqueue(new Callback<InventoryDetails>() {
            public void onResponse(Call<InventoryDetails> call, Response<InventoryDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<InventoryDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 添加手动入库单
     */
    public static void addSdEnter(AddSdEnterP addSdEnterP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addSdEnter(addSdEnterP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取手动入库列表
     */
    public static void getSdEnterList(String startPurcDate,String endPurcDate,String startCreateDate,String endCreateDate,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSdEnterList(startPurcDate,endPurcDate,startCreateDate,endCreateDate,page,limit).enqueue(new Callback<SdEnter>() {
            public void onResponse(Call<SdEnter> call, Response<SdEnter> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SdEnter> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取手动入库详情
     */
    public static void getSdEnterDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSdEnterDetails(id).enqueue(new Callback<SdEnterDetails>() {
            public void onResponse(Call<SdEnterDetails> call, Response<SdEnterDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SdEnterDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取物料库存
     */
    public static void getMaterialInventory(String prop3,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getMaterialInventory(prop3).enqueue(new Callback<MaterialInventory>() {
            public void onResponse(Call<MaterialInventory> call, Response<MaterialInventory> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<MaterialInventory> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }



    /**
     * 生产-申请出库-新增
     */
    public static void addOutBoundByProduct(AddOutBoundByProductP addOutBoundByProductP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addOutBoundByProduct(addOutBoundByProductP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 根据出库id查询生产出入库、余废料明细
     */
    public static void getProductProgress(int requireId,int deptId,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getProductProgress(requireId,deptId).enqueue(new Callback<ProductProgress>() {
            public void onResponse(Call<ProductProgress> call, Response<ProductProgress> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ProductProgress> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 修改出入库状态
     */
    public static void updateProductStatus(UpdateProductP updateProductP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).updateProductStatus(updateProductP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取部门列表
     */
    public static void getDeptList(String name,String parentId,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDeptList(name,parentId).enqueue(new Callback<Dept>() {
            public void onResponse(Call<Dept> call, Response<Dept> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Dept> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 申请入库
     */
    public static void addPutStorage(AddPutStorageP addPutStorageP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addPutStorage(addPutStorageP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 根据部门id查询出库单列表
     */
    public static void getOutBoundProductList(String planId,String deptId,String outStatus,String entryStatus,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOutBoundProductList(planId,deptId,outStatus,entryStatus,page,limit).enqueue(new Callback<OutBoundProduct>() {
            public void onResponse(Call<OutBoundProduct> call, Response<OutBoundProduct> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<OutBoundProduct> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取客户跟进列表
     */
    public static void getFollow(int customerId,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getFollow(customerId,page,limit).enqueue(new Callback<Log>() {
            public void onResponse(Call<Log> call, Response<Log> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Log> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取需要审核的出库单列表
     */
    public static void getOutBoundListByAudit(String stateStr,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOutBoundListByAudit(stateStr,page,limit).enqueue(new Callback<OutBound>() {
            public void onResponse(Call<OutBound> call, Response<OutBound> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<OutBound> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 审核-出库单
     */
    public static void AuditOutBound(AuditOutBoundP auditOutBoundP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).AuditOutBound(auditOutBoundP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取审核-生产计划
     */
    public static void getAuditPlan(String statusStr,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getAuditPlan(statusStr,page,limit).enqueue(new Callback<ProductPlan>() {
            public void onResponse(Call<ProductPlan> call, Response<ProductPlan> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ProductPlan> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 审核-生产计划
     */
    public static void AuditPlan(AuditOutBoundP auditOutBoundP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).AuditPlan(auditOutBoundP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取审核-采购单
     */
    public static void getAuditProcurementList(String statusStr,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getAuditProcurementList(statusStr,page,limit).enqueue(new Callback<Procurement>() {
            public void onResponse(Call<Procurement> call, Response<Procurement> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Procurement> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取采购单列表
     */
    public static void getProcurementList(String startDate,String endDate, String purcOrder,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getProcurementList(startDate,endDate,purcOrder,page,limit).enqueue(new Callback<Procurement>() {
            public void onResponse(Call<Procurement> call, Response<Procurement> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Procurement> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取采购单详情
     */
    public static void getProcurementDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getProcurementDetails(id).enqueue(new Callback<ProcurementDetails>() {
            public void onResponse(Call<ProcurementDetails> call, Response<ProcurementDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ProcurementDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }



    /**
     * 根据首字母 获取供应商列表
     */
    public static void getSupplierNameByName(String prop1,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSupplierNameByName(prop1).enqueue(new Callback<SupplierName>() {
            public void onResponse(Call<SupplierName> call, Response<SupplierName> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SupplierName> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 新增采购单
     */
    public static void AddProcurement(AddProcurementP addProcurementP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).AddProcurement(addProcurementP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 审核-采购单
     */
    public static void AuditProcurement(AuditOutBoundP auditOutBoundP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).AuditProcurement(auditOutBoundP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取审核-财务列表
     */
    public static void getAuditFinancialList(String stateStr,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getAuditFinancialList(stateStr,page,limit).enqueue(new Callback<Financial>() {
            public void onResponse(Call<Financial> call, Response<Financial> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Financial> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 审核-财务
     */
    public static void AuditFinancial(AuditOutBoundP auditOutBoundP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).AuditFinancial(auditOutBoundP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取收支对比
     */
    public static void getIncome(String startDate,String endDate,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getIncome(startDate,endDate).enqueue(new Callback<Income>() {
            public void onResponse(Call<Income> call, Response<Income> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Income> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 客户状态统计
     */
    public static void getCustomerState(final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getCustomerState().enqueue(new Callback<CustomerState>() {
            public void onResponse(Call<CustomerState> call, Response<CustomerState> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<CustomerState> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 销售单数及销售金额统计
     */
    public static void getStatistionSales(String endDate,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getStatistionSales(endDate).enqueue(new Callback<StatisticalSales>() {
            public void onResponse(Call<StatisticalSales> call, Response<StatisticalSales> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<StatisticalSales> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 原料消耗月度统计
     */
    public static void getStatisticalMaterial(String endDate,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getStatisticalMaterial(endDate).enqueue(new Callback<StatisticalMaterial>() {
            public void onResponse(Call<StatisticalMaterial> call, Response<StatisticalMaterial> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<StatisticalMaterial> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 成品统计
     */
    public static void getStatisticalGoods(final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getStatisticalGoods().enqueue(new Callback<StatisticalGoods>() {
            public void onResponse(Call<StatisticalGoods> call, Response<StatisticalGoods> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<StatisticalGoods> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 校验供应商名称唯一性
     */
    public static void checkSupplierName(String supplierName,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).checkSupplierName(supplierName).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 新增供应商
     */
    public static void addSupplier(AddSupplierP addSupplierP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addSupplier(addSupplierP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 编辑供应商基本信息
     */
    public static void UpdateSupplier(AddSupplierP addSupplierP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).UpdateSupplier(addSupplierP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 查询供应商列表
     */
    public static void getSupplierList(String id,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSupplierList(id,page,limit).enqueue(new Callback<Supplier>() {
            public void onResponse(Call<Supplier> call, Response<Supplier> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Supplier> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 供应商明细
     */
    public static void getSupplierDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSupplierDetails(id).enqueue(new Callback<SupplierDetails>() {
            public void onResponse(Call<SupplierDetails> call, Response<SupplierDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SupplierDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 修改明细物料单价
     */
    public static void editSupplierPrice(EditSupplierGoodsP editSupplierGoods, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).editSupplierPrice(editSupplierGoods).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 删除供应商明细
     */
    public static void deleteSupplierGoods(int id, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).deleteSupplierGoods(id).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 根据首字母查询供应商物料信息
     */
    public static void getSupplierDetails(String prop3, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSupplierDetails(prop3).enqueue(new Callback<SupplierMaterial>() {
            public void onResponse(Call<SupplierMaterial> call, Response<SupplierMaterial> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SupplierMaterial> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 新增供应商物料明细
     */
    public static void AddSupplierMaterial(AddSupplierMaterialP addSupplierMaterialP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).AddSupplierMaterial(addSupplierMaterialP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }



    /**
     * 工资列表
     */
    public static void getWageList(String createId,String deptId,String month,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getWageList(createId,deptId,month).enqueue(new Callback<Wage>() {
            public void onResponse(Call<Wage> call, Response<Wage> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Wage> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 修改采购单
     */
    public static void EditProcurement(AddProcurementP addProcurementP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).EditProcurement(addProcurementP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 出库单-修改
     */
    public static void updateOutOrder(SalesOutBoundP salesOutBoundP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).updateOutOrder(salesOutBoundP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }



    /**
     * 修改财务报销
     */
    public static void updateFinancial(UpdateFinancial transferP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).updateFinancial(transferP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 修改设备
     */
    public static void updateDevice(AddDeviceP addDeviceP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).updateDevice(addDeviceP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 查询请领表
     */
    public static void getLedTable(String startDate,String endDate,String deptId,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getLedTable(startDate,endDate,deptId,page,limit).enqueue(new Callback<LedTable>() {
            public void onResponse(Call<LedTable> call, Response<LedTable> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<LedTable> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取客户列表用户统计
     */
    public static void getCustomerByStatistical(final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getCustomerByStatistical(2).enqueue(new Callback<CustomerList>() {
            public void onResponse(Call<CustomerList> call, Response<CustomerList> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<CustomerList> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 新增请领表
     */
    public static void addLedTable(AddLedTableP addLedTable,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addLedTable(addLedTable).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取请领表详情
     */
    public static void getLedTableDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getLedTableDetails(id).enqueue(new Callback<LedTableDetails>() {
            public void onResponse(Call<LedTableDetails> call, Response<LedTableDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<LedTableDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 更新单条入库明细
     */
    public static void updateEntryGood(UpdateEntryGoodP updateEntryGoodP,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).updateEntryGood(updateEntryGoodP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 更新余废料单条记录
     */
    public static void updateWaste(UpdateWasteP updateWasteP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).updateWaste(updateWasteP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 公告列表查询
     */
    public static void getNoticeList(int type,int page,int limit,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getNoticeList(type,page,limit).enqueue(new Callback<Notice>() {
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Notice> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 文件下载
     */
    public static void download(final DownLoad downLoad, final NetWorkCallBack netWorkCallBack) {
        Http.dowload(downLoad.getDownPath(), downLoad.getSavePath(),netWorkCallBack, new okhttp3.Callback() {
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(e.getMessage());
            }
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if(response.isSuccessful()){
                    DialogUtil.closeProgress();
                    netWorkCallBack.onSuccess(response.body());
                }
            }
        });
    }


    /**
     * 客户审核
     */
    public static void auditCustomer(CustomerAuditP customerAuditP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).auditCustomer(customerAuditP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 售卖出库表
     */
    public static void getSellingList(String stateStr,String startDate,String endDate,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSellingList(stateStr,startDate,endDate,page,limit).enqueue(new Callback<SellingOutBound>() {
            public void onResponse(Call<SellingOutBound> call, Response<SellingOutBound> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SellingOutBound> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 售卖明细
     */
    public static void getSellingDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSellingDetails(id).enqueue(new Callback<SellingDetails>() {
            public void onResponse(Call<SellingDetails> call, Response<SellingDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SellingDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 修改密码
     */
    public static void updatePwd(UpdatePwdP updatePwdP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).updatePwd(updatePwdP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取仓库树状列表
     */
    public static void getStockList(final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getStockList().enqueue(new Callback<StockList>() {
            public void onResponse(Call<StockList> call, Response<StockList> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<StockList> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 生产出库记录查询
     */
    public static void getProductOutList(String deptId,String createId,String startDate,String endDate,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getProductOutList(2,deptId,createId,startDate,endDate,page,limit).enqueue(new Callback<OutAndEntry>() {
            public void onResponse(Call<OutAndEntry> call, Response<OutAndEntry> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<OutAndEntry> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 生产入库记录查询
     */
    public static void getProductEntryList(String deptId,String createId,String startDate,String endDate,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getProductEntryList(2,deptId,createId,startDate,endDate,page,limit).enqueue(new Callback<OutAndEntry>() {
            public void onResponse(Call<OutAndEntry> call, Response<OutAndEntry> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<OutAndEntry> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 新增付款明细
     */
    public static void addPayMent(AddPaymentP addPaymentP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addPayMent(addPaymentP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 工人工资明细
     */
    public static void getWorkerDetails(String month,int createId,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getWorkerDetails(month,createId,page,limit).enqueue(new Callback<WorkerDetails>() {
            public void onResponse(Call<WorkerDetails> call, Response<WorkerDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<WorkerDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 销售工资统计
     */
    public static void getSalesWage(String salesId,String month,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSalesWage(salesId,month).enqueue(new Callback<SalesWage>() {
            public void onResponse(Call<SalesWage> call, Response<SalesWage> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SalesWage> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 销售工资明细
     */
    public static void getSalesWageDetails(int salesId,String month,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSalesWageDetails(salesId,month,page,limit).enqueue(new Callback<SalesWageDetails>() {
            public void onResponse(Call<SalesWageDetails> call, Response<SalesWageDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SalesWageDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }



    /**
     * 录入客户奖金统计
     */
    public static void getEntryBonus(String createId,String month,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getEntryBonus(createId,month).enqueue(new Callback<EntryBonus>() {
            public void onResponse(Call<EntryBonus> call, Response<EntryBonus> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<EntryBonus> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 录入客户奖金明细
     */
    public static void getEntryBonusDetails(int createId,String month,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getEntryBonusDetails(createId,month,page,limit).enqueue(new Callback<EntryBonusDetails>() {
            public void onResponse(Call<EntryBonusDetails> call, Response<EntryBonusDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<EntryBonusDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 审核-售卖出库
     */
    public static void AuditSelling(AuditOutBoundP auditOutBoundP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).AuditSelling(auditOutBoundP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }

}
