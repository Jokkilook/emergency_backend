package daelim.emergency_backend.models.SevereCaseAcceptanceInfo

data class SevereCaseAcceptanceInfo(
    val dutyName:String, //기관명
    val hpid:String,//기관ID
    val mkioskty28:Boolean, //응급실 가용 여부
    val mkioskty1:Boolean, //재관류중재술 심근경색 가용 여부
    val mkioskty2:Boolean, //재관류중재술 뇌경색 가용 여부
    val mkioskty3:Boolean, //뇌출혈수술 거미막하출혈 가용 여부
    val mkioskty4:Boolean, //뇌출혈수술 거미막하출혈 외 가용 여부
    val mkioskty5:Boolean, //대동맥응급 흉부 가용 여부
    val mkioskty6:Boolean, //대동맥응급 복부 가용 여부
    val mkioskty7:Boolean, //담낭담관질환 담낭질환 가용 여부
    val mkioskty8:Boolean, //담낭담관질환 담도포함질환 가용 여부
    val mkioskty9:Boolean, //복부응급수술 비외상 가용 여부
    val mkioskty10:Boolean, //장중첩/폐색 영유아 가용 여부
    val mkioskty11:Boolean, //응급내시경 성인 위장관 가용 여부
    val mkioskty12:Boolean, //응급내시경 영유아 위장관 가용 여부
    val mkioskty13:Boolean, //응급내시경 성인 기관지 가용 여부
    val mkioskty14:Boolean, //응급내시경 영유아 기관지 가용 여부
    val mkioskty15:Boolean, //저출생체중아 집중치료 가용 여부
    val mkioskty16:Boolean, //산부인과응급 분만 가용 여부
    val mkioskty17:Boolean, //산부인과응급 산과수술 가용 여부
    val mkioskty18:Boolean, //산부인과응급 부인과수술 가용 여부
    val mkioskty19:Boolean, //중증화상 전문치료 가용 여부
    val mkioskty20:Boolean, //사지접합 수족지접합 가용 여부
    val mkioskty21:Boolean, //사지접합 수족지접합 외 가용 여부
    val mkioskty22:Boolean, //응급투석 HD 가용 여부
    val mkioskty23:Boolean, //응급투석 CRRT 가용 여부
    val mkioskty24:Boolean, //정신과적응급 폐쇄병동입원 가용 여부
    val mkioskty25:Boolean, //안과적수술 응급 가용 여부
    val mkioskty26:Boolean, //영상의학혈관중재 성인 가용 여부
    val mkioskty27:Boolean, //영상의학혈관중애 영유아 가용 여부
    val M_KOISK_TY10_MSG:String, //장중첩/폐색(영유아) 가능연령
    val M_KOISK_TY12_MSG:String, //위장관 응급내시경(영유아) 가능연령
    val M_KOISK_TY14_MSG:String, //기관지 응급내시경(영유아) 가능연령
    val M_KOISK_TY15_MSG:String, //저출생 체중아 가능연령
    val M_KOISK_TY27_MSG:String, //영상의학 혈관 중재적 시술(영유아) 가능연령
)
