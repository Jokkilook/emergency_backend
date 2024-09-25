package daelim.emergency_backend.models.EmergencyAndSevereCaseMessage

data class EmergencyAndSevereCaseMessage(
    val rnum: Int, //일련번호
    val dutyAddr: String, //기관주소
    val dutyName: String, //기관명
    val emcOrgCod: String, //기관코드(기관ID)
    val hpid: String, //기관코드
    val symBlkMsq: String, //전달메세지
    val symBlkMsqTyp: String, //메세지구분
    val symTypCod: String, //중층징환구분
    val symTypMag: String, //중증질환명
    val synOutDspYon: Boolean, //중증질환 표출 구분 (Y:해제, N:차단)
    val sysOutDspMth: Boolean, //표출 차단구분 (Y:자동, N:수동)
    val symBlkSttDtm: Int, //차단시작
    val sysBlkEndDtm: Int, //차단종료
)