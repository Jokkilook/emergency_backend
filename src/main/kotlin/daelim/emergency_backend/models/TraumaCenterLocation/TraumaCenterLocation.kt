package daelim.emergency_backend.models.TraumaCenterLocation

data class TraumaCenterLocation(
    val mum: Int, //일련번호
    val cnt: Int, //건수
    val distance: Int, //거리
    val dutyAddr: String, //주소
    val dutyDiv: String, //병원분류
    val dutyDivName: String, //병원분류명
    val dutyTel1: String, //대표전화1
    val endTime: Int, //종료시간
    val hpid: String, //기관ID
    val ltitude: Double, //병원위도
    val longitude: Double, //병원경도
    val startTime: Int, //시작시간
)