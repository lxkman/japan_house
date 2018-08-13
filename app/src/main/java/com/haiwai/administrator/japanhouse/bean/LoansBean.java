package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/5/9.
 */

public class LoansBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"nameCn":"1","nameJpn":"1","rateCn":"1","rateJpn":"1","ageLimitCn":"1","ageLimitJpn":"1","limitRangeCn":"1","limitRangeJpn":"1","lendingWayCn":"1","lendingWayJpn":"1","paymentScheduleCn":"1","paymentScheduleJpn":"1","admissionRequirementsCn":"1","admissionRequirementsJpn":"1","censusRegisterCn":"1","censusRegisterJpn":"1","materialsCn":"1","materialsJpn":"1","hotLineCn":null,"hotLineJpn":"1","isDeleted":0}]
     */

    private String msg;
    private String code;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 1
         * nameCn : 1
         * nameJpn : 1
         * rateCn : 1
         * rateJpn : 1
         * ageLimitCn : 1
         * ageLimitJpn : 1
         * limitRangeCn : 1
         * limitRangeJpn : 1
         * lendingWayCn : 1
         * lendingWayJpn : 1
         * paymentScheduleCn : 1
         * paymentScheduleJpn : 1
         * admissionRequirementsCn : 1
         * admissionRequirementsJpn : 1
         * censusRegisterCn : 1
         * censusRegisterJpn : 1
         * materialsCn : 1
         * materialsJpn : 1
         * hotLineCn : null
         * hotLineJpn : 1
         * isDeleted : 0
         */

        private int id;
        private String nameCn;
        private String nameJpn;
        private String rateCn;
        private String rateJpn;
        private String ageLimitCn;
        private String ageLimitJpn;
        private String limitRangeCn;
        private String limitRangeJpn;
        private String lendingWayCn;
        private String lendingWayJpn;
        private String paymentScheduleCn;
        private String paymentScheduleJpn;
        private String admissionRequirementsCn;
        private String admissionRequirementsJpn;
        private String censusRegisterCn;
        private String censusRegisterJpn;
        private String materialsCn;
        private String materialsJpn;
        private String hotLineCn;
        private String hotLineJpn;
        private int isDeleted;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNameCn() {
            return nameCn;
        }

        public void setNameCn(String nameCn) {
            this.nameCn = nameCn;
        }

        public String getNameJpn() {
            return nameJpn;
        }

        public void setNameJpn(String nameJpn) {
            this.nameJpn = nameJpn;
        }

        public String getRateCn() {
            return rateCn;
        }

        public void setRateCn(String rateCn) {
            this.rateCn = rateCn;
        }

        public String getRateJpn() {
            return rateJpn;
        }

        public void setRateJpn(String rateJpn) {
            this.rateJpn = rateJpn;
        }

        public String getAgeLimitCn() {
            return ageLimitCn;
        }

        public void setAgeLimitCn(String ageLimitCn) {
            this.ageLimitCn = ageLimitCn;
        }

        public String getAgeLimitJpn() {
            return ageLimitJpn;
        }

        public void setAgeLimitJpn(String ageLimitJpn) {
            this.ageLimitJpn = ageLimitJpn;
        }

        public String getLimitRangeCn() {
            return limitRangeCn;
        }

        public void setLimitRangeCn(String limitRangeCn) {
            this.limitRangeCn = limitRangeCn;
        }

        public String getLimitRangeJpn() {
            return limitRangeJpn;
        }

        public void setLimitRangeJpn(String limitRangeJpn) {
            this.limitRangeJpn = limitRangeJpn;
        }

        public String getLendingWayCn() {
            return lendingWayCn;
        }

        public void setLendingWayCn(String lendingWayCn) {
            this.lendingWayCn = lendingWayCn;
        }

        public String getLendingWayJpn() {
            return lendingWayJpn;
        }

        public void setLendingWayJpn(String lendingWayJpn) {
            this.lendingWayJpn = lendingWayJpn;
        }

        public String getPaymentScheduleCn() {
            return paymentScheduleCn;
        }

        public void setPaymentScheduleCn(String paymentScheduleCn) {
            this.paymentScheduleCn = paymentScheduleCn;
        }

        public String getPaymentScheduleJpn() {
            return paymentScheduleJpn;
        }

        public void setPaymentScheduleJpn(String paymentScheduleJpn) {
            this.paymentScheduleJpn = paymentScheduleJpn;
        }

        public String getAdmissionRequirementsCn() {
            return admissionRequirementsCn;
        }

        public void setAdmissionRequirementsCn(String admissionRequirementsCn) {
            this.admissionRequirementsCn = admissionRequirementsCn;
        }

        public String getAdmissionRequirementsJpn() {
            return admissionRequirementsJpn;
        }

        public void setAdmissionRequirementsJpn(String admissionRequirementsJpn) {
            this.admissionRequirementsJpn = admissionRequirementsJpn;
        }

        public String getCensusRegisterCn() {
            return censusRegisterCn;
        }

        public void setCensusRegisterCn(String censusRegisterCn) {
            this.censusRegisterCn = censusRegisterCn;
        }

        public String getCensusRegisterJpn() {
            return censusRegisterJpn;
        }

        public void setCensusRegisterJpn(String censusRegisterJpn) {
            this.censusRegisterJpn = censusRegisterJpn;
        }

        public String getMaterialsCn() {
            return materialsCn;
        }

        public void setMaterialsCn(String materialsCn) {
            this.materialsCn = materialsCn;
        }

        public String getMaterialsJpn() {
            return materialsJpn;
        }

        public void setMaterialsJpn(String materialsJpn) {
            this.materialsJpn = materialsJpn;
        }

        public String getHotLineCn() {
            return hotLineCn;
        }

        public void setHotLineCn(String hotLineCn) {
            this.hotLineCn = hotLineCn;
        }

        public String getHotLineJpn() {
            return hotLineJpn;
        }

        public void setHotLineJpn(String hotLineJpn) {
            this.hotLineJpn = hotLineJpn;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }
    }
}
