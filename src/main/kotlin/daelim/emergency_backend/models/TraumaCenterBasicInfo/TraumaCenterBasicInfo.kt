package daelim.emergency_backend.models.TraumaCenterBasicInfo

data class TraumaCenterBasicInfo(
    val hpid: String, //기관ID
    val dutyName: String, //기관명
    val postCdn1: Int, //우편번호1
    val postCdn2: Int, //우편번호2
    val dutyAddr: String, //주소
    val dutyTel1: String, //대표전화1
    val dutyTel3: String, //응급실전화
    val hvec: Int, //응급실
    val hvoc: Int, //수술실
    val hvcc: Int, //신경수술실
    val hvccc: Int, //흉부중환자
    val hvicc: Int, //일반중환자
    val hvgc: Int, //입원실
    val dutyHayn: Boolean, //입원실가능여부(1/2)
    val dutyHano: Int, //병상수
    val dutyInf: String, //기관설명상세
    val dutyMapimg: String, //간이약도
    val dutyEryn: Boolean, //응급실운영여부(1/2)
    val dutyTime1c: Int, //진료시간(월요일)C
    val dutyTime2c: Int, //진료시간(화요일)C
    val dutyTime3c: Int, //진료시간(수요일)C
    val dutyTime4c: Int, //진료시간(목요일)C
    val dutyTime5c: Int, //진료시간(금요일)C
    val dutyTime6c: Int, //진료시간(토요일)C
    val dutyTime7c: Int, //진료시간(일요일)C
    val dutyTime8c: Int, //진료시간(공휴일)C
    val dutyTime1s: Int, //진료시간(월요일)S
    val dutyTime2s: Int, //진료시간(화요일)S
    val dutyTime3s: Int, //진료시간(수요일)S
    val dutyTime4s: Int, //진료시간(목요일)S
    val dutyTime5s: Int, //진료시간(금요일)S
    val dutyTime6s: Int, //진료시간(토요일)S
    val dutyTime7s: Int, //진료시간(일요일)S
    val dutyTime8s: Int, //진료시간(공휴일)S
    val MKioskTy25: Boolean, //응급실(Emergency gate keeper) <- Y : 가능 N : 불가능
    val MKioskTy1: Boolean, //뇌추혈수술 <- Y : 가능 N : 불가능
    val MKioskTy2: Boolean, //뇌경색의재관류 <- Y : 가능 N : 불가능
    val MKioskTy3: Boolean, //심근경색의재관류 <- Y : 가능 N : 불가능
    val MKioskTy4: Boolean, //복부손상의수술 <- Y : 가능 N : 불가능
    val MKioskTy5: Boolean, //사지접합의수술 <- Y : 가능 N : 불가능
    val MKioskTy6: Boolean, //응급내시경 <- Y : 가능 N : 불가능
    val MKioskTy7: Boolean, //응급투석 <- Y : 가능 N : 불가능
    val MKioskTy8: Boolean, //조산산모 <- Y : 가능 N : 불가능
    val MKioskTy9: Boolean, //정신질환자 <- Y : 가능 N : 불가능
    val MKioskTy10: Boolean, //신생아 <- Y : 가능 N : 불가능
    val MKioskTy11: Boolean, //중증화상 <- Y : 가능 N : 불가능
    val wgs84Lon: Double, //병원경도
    val wgs84Lat: Double, //병원위도
    val dgidldName: String, //진료과목
    val hpbdn: Int, //병상수
    val hpccuyn: Int, //흉부중환자실
    val hpcuyn: Int, //신경중환자실
    val hperyn: Int, //응급실
    val hpgryn: Int, //입원실
    val hpcuyn: Int, //입반중환자실
    val hpnicuyn: Int, //신생아중환자실
    val hpopuyn: Int, //수술실
)