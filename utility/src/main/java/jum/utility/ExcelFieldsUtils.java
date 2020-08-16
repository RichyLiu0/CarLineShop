package jum.utility;

import com.ly.mp.common.utils.excel.ExcelField;

import java.util.ArrayList;
import java.util.List;

public class ExcelFieldsUtils {

    //  AVS
    public static List<ExcelField> getExcelFields() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("VIN_NO", "车架号"));
        excelFields.add(new ExcelField("MODEL", "车型"));
        excelFields.add(new ExcelField("MODEL_YEAR", "年份"));
        excelFields.add(new ExcelField("WHOLESALE_DATE", "批售日期"));
        excelFields.add(new ExcelField("WHOLESALE_MONTH", "批售月份"));
        excelFields.add(new ExcelField("RETAIL_DATE", "零售日期"));
        excelFields.add(new ExcelField("RETAIL_MONTH", "零售月份"));
        excelFields.add(new ExcelField("GA_HO_DATE", "总装下线日期"));
        excelFields.add(new ExcelField("GA_MONTH", "总装月份"));
        excelFields.add(new ExcelField("QUALITY_BO_DATE", "生产交付日期"));
        excelFields.add(new ExcelField("QUALITY_BO_MONTH", "生产交付月份"));
        excelFields.add(new ExcelField("WARRANTY_START_DATE", "保修开始日期"));
        excelFields.add(new ExcelField("WARRANTY_START_MONTH", "保修开始月份"));
        excelFields.add(new ExcelField("REPORT_DATE", "报告日期"));
        excelFields.add(new ExcelField("RANNING_DAYS", "运行天数"));
        excelFields.add(new ExcelField("TAXI_OR_NOT", "车辆用途"));
        excelFields.add(new ExcelField("OVER_INSURANCE_OR_NOT", "是否超保"));
        excelFields.add(new ExcelField("DESPATCH_DATE", "发运日期"));
        excelFields.add(new ExcelField("DEALER_CAR_COLLECT_DATE", "经销商收车日期"));
        excelFields.add(new ExcelField("DEALER_CODE", "经销商代码"));
        excelFields.add(new ExcelField("CONFIGURE_CODE", "配置代码"));
        return excelFields;
    }

    //  索赔单
    public static List<ExcelField> getExcelFields1() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("claimNo", "索赔编号"));
        excelFields.add(new ExcelField("dealerCode", "经销商编码"));
        excelFields.add(new ExcelField("dealerName", "经销商名称"));
        excelFields.add(new ExcelField("province", "经销商省份"));
        excelFields.add(new ExcelField("claimType", "索赔类型"));
        excelFields.add(new ExcelField("model", "车型"));
        excelFields.add(new ExcelField("vin", "车架号"));
        excelFields.add(new ExcelField("crStatus", "CR状态"));
        excelFields.add(new ExcelField("configureCode", "配置代码"));
        excelFields.add(new ExcelField("odometer", "里程数"));
        excelFields.add(new ExcelField("mileageRounded", "近似里程"));
        excelFields.add(new ExcelField("failureDate", "故障日期"));
        excelFields.add(new ExcelField("failureMonth", "故障月份"));
        excelFields.add(new ExcelField("repairDate", "修理日期"));
        excelFields.add(new ExcelField("repairMonth", "修理月"));
        excelFields.add(new ExcelField("approvedDate", "批准日期"));
        excelFields.add(new ExcelField("approvedMonth", "批准月"));
        excelFields.add(new ExcelField("symptomCode", "症状代码"));
        excelFields.add(new ExcelField("symptomDesc", "症状描述"));
        excelFields.add(new ExcelField("partCode", "配件编码"));
        excelFields.add(new ExcelField("partNameEn", "配件名称"));
        excelFields.add(new ExcelField("partCost", "配件价格"));
        excelFields.add(new ExcelField("laborCost", "工时费"));
        excelFields.add(new ExcelField("otherCost", "其他费用"));
        excelFields.add(new ExcelField("totalCost", "总成本"));
        excelFields.add(new ExcelField("gaBoDate", "总装下线日期"));
        excelFields.add(new ExcelField("gaBoMonth", "总装下线月份"));
        excelFields.add(new ExcelField("handoverDate", "生产交付日期"));
        excelFields.add(new ExcelField("handoverMonth", "生产交付月份"));
        excelFields.add(new ExcelField("shipDate", "发运日期"));
        excelFields.add(new ExcelField("dealerReceiveDate", "经销商接车日期"));
        excelFields.add(new ExcelField("retailDate", "零售日期"));
        excelFields.add(new ExcelField("retailMonth", "零售月份"));
        excelFields.add(new ExcelField("warrantyStartDate", "保修开始日期"));
        excelFields.add(new ExcelField("warrantyStartMonth", "保修开始月份"));
        excelFields.add(new ExcelField("module", "模块"));
        excelFields.add(new ExcelField("primaryClassification", "一级分类"));
        excelFields.add(new ExcelField("secondaryClassification", "二级分类"));
        excelFields.add(new ExcelField("threeLeverClassification", "三级分类"));
        excelFields.add(new ExcelField("laborCode", "工时代码"));
        excelFields.add(new ExcelField("taxi", "车辆用途"));
        excelFields.add(new ExcelField("claimStatus", "索赔单状态"));
        excelFields.add(new ExcelField("engineSn", "发动机号"));
        excelFields.add(new ExcelField("transmissionSn", "变速箱号"));
        excelFields.add(new ExcelField("supplierCode", "供应商代码"));
        excelFields.add(new ExcelField("supplierName", "供应商名称"));
        excelFields.add(new ExcelField("misNo", "MIS_NO"));
        excelFields.add(new ExcelField("issueDesc", "问题代码"));
        excelFields.add(new ExcelField("tssNo", "技术报告编号"));
        excelFields.add(new ExcelField("dtcCode", "DTC代码"));
        excelFields.add(new ExcelField("dutyDepartment", "责任部门"));
        excelFields.add(new ExcelField("asDeductStatus", "AS抵扣状态"));
        excelFields.add(new ExcelField("returnStatus", "旧件回运状态"));
        excelFields.add(new ExcelField("returnResult", "回运测试结果"));
        excelFields.add(new ExcelField("crNo", "CR编号"));
        excelFields.add(new ExcelField("deductionStatus", "扣款状态"));
        excelFields.add(new ExcelField("projectCode", "项目编号"));
        excelFields.add(new ExcelField("fqFaultDesc", "项目故障描述"));
        excelFields.add(new ExcelField("lastUpdatedDate", "最后修改时间"));
        excelFields.add(new ExcelField("modifier", "修改人"));
        return excelFields;
    }


    //  旧件回运
    public static List<ExcelField> getExcelFields2() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("return_mode", "回运模式"));
        excelFields.add(new ExcelField("supplier_code", "供应商代码"));
        excelFields.add(new ExcelField("dealer_code", "经销商代码"));
        excelFields.add(new ExcelField("dealer_name", "经销商名称"));
        excelFields.add(new ExcelField("return_case_no", "退运箱号"));
        excelFields.add(new ExcelField("size", "尺寸"));
        excelFields.add(new ExcelField("claim_no", "索赔单号"));
        excelFields.add(new ExcelField("rma", "RMA"));
        excelFields.add(new ExcelField("vin", "车架号"));
        excelFields.add(new ExcelField("part_number", "零件号"));
        excelFields.add(new ExcelField("part_description", "零件名称"));
        excelFields.add(new ExcelField("qty", "数量"));
        excelFields.add(new ExcelField("warehouse_receipt_number", "仓库收货数量"));
        excelFields.add(new ExcelField("save_position", "存储仓位"));
        excelFields.add(new ExcelField("comments", "备注"));
        excelFields.add(new ExcelField("those_responsible", "责任人"));
        excelFields.add(new ExcelField("warranty_parts_status", "保修件状态"));
        excelFields.add(new ExcelField("destination", "备货目的地"));
        excelFields.add(new ExcelField("stoc_up_date", "待备货日期"));
        excelFields.add(new ExcelField("prepared_date", "已备货日期"));
        excelFields.add(new ExcelField("warehouse_receipt_date", "仓库收货时间"));
        excelFields.add(new ExcelField("warehouse_out_date", "仓库出库时间"));
        excelFields.add(new ExcelField("scrap_date", "报废日期"));
        excelFields.add(new ExcelField("take_time", "取件时效"));
        return excelFields;
    }


    //  问题代码
    public static List<ExcelField> getExcelFields3() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("model", "所在模块"));
        excelFields.add(new ExcelField("first_level_sort", "一级分类"));
        excelFields.add(new ExcelField("second_level_sort", "二级分类"));
        excelFields.add(new ExcelField("three_level_sort", "三级分类"));
        excelFields.add(new ExcelField("issue_code", "问题代码"));
        excelFields.add(new ExcelField("remark", "问题描述"));
        excelFields.add(new ExcelField("creator", "创建人"));
        excelFields.add(new ExcelField("created_date", "创建时间"));
        excelFields.add(new ExcelField("modifier", "最后更新人"));
        excelFields.add(new ExcelField("last_updated_date", "最后更新时间"));
        return excelFields;
    }

    //  供应商信息
    public static List<ExcelField> getExcelFields10() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("VENDOR", "供应商编码"));
        excelFields.add(new ExcelField("NAME", "供应商名称"));
        excelFields.add(new ExcelField("SEARCH_TERM", "searchTerm"));
        excelFields.add(new ExcelField("POSTAL_CODE", "邮政编码"));
        excelFields.add(new ExcelField("CITY", "所在城市"));
        excelFields.add(new ExcelField("STREET", "所在街道"));
        excelFields.add(new ExcelField("COUNTRY", "所在国家"));
        excelFields.add(new ExcelField("EMAIL", "邮编"));
        excelFields.add(new ExcelField("RATE_FILE", "费率文件"));
        excelFields.add(new ExcelField("RATE_MANAGEMENT", "费率管理"));
        return excelFields;
    }

    //  法规标准值
    public static List<ExcelField> getExcelFields4() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("car_type", "车型"));
        excelFields.add(new ExcelField("zero_parts", "零部件"));
        excelFields.add(new ExcelField("standard_val", "法规标准值"));
        excelFields.add(new ExcelField("email_reminder", "邮件提醒人"));
        excelFields.add(new ExcelField("created_date", "创建时间"));
        excelFields.add(new ExcelField("creator", "创建人"));
        excelFields.add(new ExcelField("last_updated_date", "最后更新时间"));
        excelFields.add(new ExcelField("modifier", "最后更新人"));
        return excelFields;
    }


    //  SBOM数据维护
    public static List<ExcelField> getExcelFields5() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("PART_NR", "零件号"));
        excelFields.add(new ExcelField("MODEL", "车型"));
        excelFields.add(new ExcelField("SUPPLIER_A", "供应商A"));
        excelFields.add(new ExcelField("SUPPLIER_B", "供应商B"));
        excelFields.add(new ExcelField("MODULE_IN_QPLM", "模块代码"));
        excelFields.add(new ExcelField("LEVEL_ONE", "一级分类"));
        excelFields.add(new ExcelField("LEVEL_TWO", "二级分类"));
        excelFields.add(new ExcelField("LEVEL_THREE", "三级分类"));
        excelFields.add(new ExcelField("ENGLISH_PART_NAME", "英文名"));
        excelFields.add(new ExcelField("CHINESE_PART_NAME", "中文名"));
        return excelFields;
    }


    //  CR清单查询
    public static List<ExcelField> getExcelFields6() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("crNo", "CR单号"));
        excelFields.add(new ExcelField("supplierCode", "供应商代码"));
        excelFields.add(new ExcelField("supplierName", "供应商名称"));
        excelFields.add(new ExcelField("wfStatus", "当前状态"));
        excelFields.add(new ExcelField("manageCostAmt", "管理费"));
        excelFields.add(new ExcelField("laborCostAmt", "工时费"));
        excelFields.add(new ExcelField("partCostAmt", "零件价格"));
        excelFields.add(new ExcelField("absorptionRate", "分摊率"));
        excelFields.add(new ExcelField("totalCostAmt", "总费用"));
        excelFields.add(new ExcelField("text", "发票号"));
        excelFields.add(new ExcelField("documentDate", "开票日期"));
        excelFields.add(new ExcelField("crossAmt", "发票金额"));
        excelFields.add(new ExcelField("initationMan", "发起人"));
        excelFields.add(new ExcelField("initationDate", "发起日期"));
        return excelFields;
    }

    //  车辆销售信息
    public static List<ExcelField> getExcelFields7() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("VIN", "车架号"));
        excelFields.add(new ExcelField("PLANNED_ORDER_NUMBER", "生产订单号"));
        excelFields.add(new ExcelField("MODEL", "车型"));
        excelFields.add(new ExcelField("MODEL_YEAR", "车型年"));
        excelFields.add(new ExcelField("DEALER_CODE", "经销商代码"));
        excelFields.add(new ExcelField("DEALER_NAME", "经销商名称"));
        excelFields.add(new ExcelField("BATCH_SELL", "批售日期"));
        excelFields.add(new ExcelField("ORDER_STATUS", "订单状态"));
        excelFields.add(new ExcelField("TAKE_CAR_DATE", "收车日期"));
        excelFields.add(new ExcelField("RETAIL_DATE", "零售日期"));
        excelFields.add(new ExcelField("CONFIGURE_CODE", "配置代码"));
        excelFields.add(new ExcelField("taxi", "车辆用途"));
        excelFields.add(new ExcelField("warranty_start_date", "保修开始时间"));
        return excelFields;
    }


    //  售后维修信息
    public static List<ExcelField> getExcelFields8() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("DEALER_CODE", "经销商代码"));
        excelFields.add(new ExcelField("DEALER_NAME", "经销商名称"));
        excelFields.add(new ExcelField("VIN", "车架号"));
        excelFields.add(new ExcelField("MODEL", "车型"));
        excelFields.add(new ExcelField("ORDER_DATE", "(工单)开单日"));
        excelFields.add(new ExcelField("MILEAGE", "里程数"));
        excelFields.add(new ExcelField("PICK_UP_DATE", "(维修)交车日期"));
        excelFields.add(new ExcelField("COMPLETION_DATE", "完工日期"));
        excelFields.add(new ExcelField("SERVICE_LINE_TYPE", "(配件)工时代码"));
        excelFields.add(new ExcelField("DESCRIPTION", "描述"));
        excelFields.add(new ExcelField("UNIT_PRICE", "单价"));
        excelFields.add(new ExcelField("QUANTITY", "数量"));
        excelFields.add(new ExcelField("AMOUNT", "金额"));
        return excelFields;
    }

    //  经销商信息
    public static List<ExcelField> getExcelDealerCode() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("CUSTOMER_NUMBER", "经销商编码"));
        excelFields.add(new ExcelField("CUSTOMER_NAME", "经销商全称"));
        excelFields.add(new ExcelField("SORT", "经销商简称"));
        excelFields.add(new ExcelField("REQUIREMENT_TYPE", "客户类型"));
        excelFields.add(new ExcelField("ACCOUNT_GROUP", "账户组"));
        excelFields.add(new ExcelField("EMAIL_ADDRESS", "邮箱地址"));
        excelFields.add(new ExcelField("FAX", "传真"));
        excelFields.add(new ExcelField("POSTAL_CODE", "邮政编码"));
        excelFields.add(new ExcelField("CITY", "所在城市"));
        excelFields.add(new ExcelField("STREET", "所在街道"));
        excelFields.add(new ExcelField("COUNTRY", "所在国家"));
        excelFields.add(new ExcelField("REGION", "地区"));
        return excelFields;
    }

    //  零件信息
    public static List<ExcelField> getExcelPart() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("MATERIAL", "零件编码"));
        excelFields.add(new ExcelField("MATERIAL_DESCRIPTION", "零件描述"));
        excelFields.add(new ExcelField("NET_PRICE", "零件价格"));
        excelFields.add(new ExcelField("PRICE_UNIT", "价格单位"));
        excelFields.add(new ExcelField("UOM", "UoM"));
        excelFields.add(new ExcelField("PRICE_VAILD_FROM", "价格有效日期起"));
        excelFields.add(new ExcelField("PRICE_VAILD_TO", "价格有效日期止"));
        excelFields.add(new ExcelField("VENDOR", "供应商代码"));
        excelFields.add(new ExcelField("VENDOR_NAME_ZH", "供应商中文名称"));
        excelFields.add(new ExcelField("VENDOR_NAME_EN", "供应商英文名称"));
        excelFields.add(new ExcelField("DOCUMENT_VALID_FROM", "文档有效日期起"));
        excelFields.add(new ExcelField("DOCUMENT_VALID_TO", "文档有效日期止"));
        return excelFields;
    }

    //  零公里索赔信息
    public static List<ExcelField> getExcelZeroClaim() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("project_code", "索赔单号"));
        excelFields.add(new ExcelField("supplier_code", "供应商编码"));
        excelFields.add(new ExcelField("supplier_name", "供应商名称"));
        excelFields.add(new ExcelField("publish_date", "发起日期"));
        excelFields.add(new ExcelField("wf_status", "当前状态"));
        excelFields.add(new ExcelField("text", "发票号"));
        excelFields.add(new ExcelField("document_date", "开票日期"));
        excelFields.add(new ExcelField("cross_amt", "发票金额"));
        return excelFields;
    }

    //  税率信息
    public static List<ExcelField> getExcelTaxRate() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("TAX_CODE", "税率编码"));
        excelFields.add(new ExcelField("TAX_NAME", "税率名称"));
        excelFields.add(new ExcelField("TAX", "税率"));
        excelFields.add(new ExcelField("OUTPUT", "是否销项税"));
        excelFields.add(new ExcelField("REGION", "地区"));
        excelFields.add(new ExcelField("IS_DEFAULT", "是否默认"));
        excelFields.add(new ExcelField("CREATOR", "创建人"));
        excelFields.add(new ExcelField("CREATED_DATE", "创建时间"));
        excelFields.add(new ExcelField("MODIFIER", "最后更新人"));
        excelFields.add(new ExcelField("LAST_UPDATED_DATE", "最后更新时间"));
        return excelFields;
    }

    //  费率信息
    public static List<ExcelField> getExcelCostRate() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("rate_code", "费率编码"));
        excelFields.add(new ExcelField("rate_name", "费率名称"));
        excelFields.add(new ExcelField("cost_rate", "费用率(Expense Rate)"));
        excelFields.add(new ExcelField("work_rate", "工时率(Rework Rate)"));
        excelFields.add(new ExcelField("creator", "创建人"));
        excelFields.add(new ExcelField("created_date", "创建时间"));
        excelFields.add(new ExcelField("modifier", "最后更新人"));
        excelFields.add(new ExcelField("last_updated_date", "最后更新时间"));
        return excelFields;
    }

    //  CR清单列表信息
    public static List<ExcelField> getExcelCrClaim() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("crNo", "CR单号"));
        excelFields.add(new ExcelField("manageCostAmt", "管理费"));
        excelFields.add(new ExcelField("laborCostAmt", "工时费"));
        excelFields.add(new ExcelField("partCostAmt", "零件价格"));
        excelFields.add(new ExcelField("absorptionRate", "分摊率"));
        excelFields.add(new ExcelField("totalCostAmt", "总费用"));
        excelFields.add(new ExcelField("wfStatus", "当前状态"));
        excelFields.add(new ExcelField("initationDate", "发起日期"));
        return excelFields;
    }

    // CR清单查询
    public static List<ExcelField> getExcelFieldCrClaim() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("crNo", "CR单号"));
        excelFields.add(new ExcelField("supplierCode", "供应商代码"));
        excelFields.add(new ExcelField("supplierName", "供应商名称"));
        excelFields.add(new ExcelField("wfStatus", "当前状态"));
        excelFields.add(new ExcelField("manageCostAmt", "管理费"));
        excelFields.add(new ExcelField("laborCostAmt", "工时费"));
        excelFields.add(new ExcelField("partCostAmt", "零件价格"));
        excelFields.add(new ExcelField("absorptionRate", "分摊率"));
        excelFields.add(new ExcelField("totalCostAmt", "总费用"));
        excelFields.add(new ExcelField("text", "发票号"));
        excelFields.add(new ExcelField("documentDate", "开票日期"));
        excelFields.add(new ExcelField("crossAmt", "发票金额"));
        excelFields.add(new ExcelField("initationMan", "发起人"));
        excelFields.add(new ExcelField("initationDate", "发起日期"));
        return excelFields;
    }


    // 车辆过点信息
    public static List<ExcelField> getExcelFieldCarPass() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("vin", "车架号"));
        excelFields.add(new ExcelField("POINT_CODE", "点位编号"));
        excelFields.add(new ExcelField("sap_order_no", "SAP订单号"));
        excelFields.add(new ExcelField("mes_production_no", "MES生产号"));
        excelFields.add(new ExcelField("PASS_TIME", "过点时间"));
        excelFields.add(new ExcelField("MESSAGE_TYPE", "报文类型"));
        excelFields.add(new ExcelField("MESSAGE_EACH_DATE", "报文交互时间"));
        excelFields.add(new ExcelField("MESSAGE_LENGTH", "报文长度"));
        return excelFields;
    }


    // 追溯信息
    public static List<ExcelField> getExcelFieldRetrospect() {
        List<ExcelField> excelFields = new ArrayList<>(10);
        excelFields.add(new ExcelField("vin", "车架号"));
        excelFields.add(new ExcelField("sap_order_no", "SAP订单号"));
        excelFields.add(new ExcelField("mes_production_no", "MES生产号"));
        excelFields.add(new ExcelField("RETROSPECT_PART_NO", "追溯件编号"));
        excelFields.add(new ExcelField("RETROSPECT_PART_VALUE", "追溯件值"));
        excelFields.add(new ExcelField("lookUpValueName", "追溯件中文名称描述"));
        excelFields.add(new ExcelField("lookUpValueEnName", "追溯件英文名称描述"));
        excelFields.add(new ExcelField("MESSAGE_TYPE", "报文类型"));
        excelFields.add(new ExcelField("MESSAGE_EACH_DATE", "报文交互时间"));
        excelFields.add(new ExcelField("MESSAGE_LENGTH", "报文长度"));
        return excelFields;
    }


}
