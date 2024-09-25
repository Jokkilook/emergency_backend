package daelim.emergency_backend.models.AvailavleBedInfo

data class AvailableBedInfo(
    val mum:Int, //일련번호
    val hpid:String, //기관코드
    val phpid:String, //구 기관코드
    val hvidate:String, //입력일시
    val hvec:Int, //일반병상 수
    val hvoc:Int, //수술실 병상 수
    val hvcc:Int, //중환자실 신경과 병상 수
    val hvncc:Int, //중환자실 신생아 병상 수
    val hvccc:Int, //중환자실 흉부외과 병상 수
    val hvicc:Int, //중환자실 일반 입원실 수
    val hvdnm:String, //당직의 이름
    val hvctayn:Boolean, //CT 가용 여부
    val hvmriayn:Boolean, //MRI 가용 여부
    val hvangioayn:Boolean, //혈관촬영기 가용 여부
    val hvventiayn:Boolean, //인공호흡기 가용 여부
    val hvventisoayn:Boolean, //인큐베이터 가용 여부
    val hvcrrtayn:Boolean, //CRRT 가용 여부
    val hvecmoayn:Boolean, //ECMO 가용 여부
    val hvoxyayn:Boolean, //고압 산소 치료기 가용 여부
    val hvhypoayn:Boolean, //중심 체온 조절 유도기 가용 여부
    val hvamyn:Boolean, //구급차가용여부
    val hv1:String, //응급실 당직의 직통 연락처
    val hv2:String, //내과 중환자실 수
    val hv3:String, //외과 중환자실 수
    val hv4:String, //외과입원실(정형외과) 수
    val hv5:String, //신경과입원실 수
    val hv6:String, //신경외과 중환자실 수
    val hv7:String, //약물중환자 수
    val hv8:String, //화상 중환자실 수
    val hv9:String, //외상 중환자실 수
    val hv10:Boolean, //소아 가능 여부
    val hv11:Boolean, //인큐베이터(보육기) 가용 여부
    val hv12:String, //솓아당직의 직통연락처
    val hv13:String, //격리진료구역 음압격리병상 수
    val hv14:String, //격리진료구역 일반격리병상 수
    val hv15:String, //소아음악격리 수
    val hv16:String, //소아일반격리 수
    val hv17:String, //응급전용 중환자실 음압격리 수
    val hv18:String, //응급전용 중환자실 일반격리 수
    val hv19:String, //응급전용 입원실 음압격리 수
    val hv21:String, //응급전용 입원실 일반격리 수
    val hv22:String, //감염병 전담병상 중환자실 내 음압격리병상 수
    val hv23:String, //감염병 전담병상 중환사실 수
    val hv24:String, //감염 중증 병상 수
    val hv25:String, //감염 준-중증 병상 수
    val hv26:String, //감염 중등증 병상 수
    val hv27:String, //코호트 격리 수
    val hv28:String, //소아 수
    val hv29:String, //응급실 음압 격리 병상 수
    val hv30:String, //응급실 일반 격리 병상 수
    val hv31:String, //응급전용 중환자실 수
    val hv32:String, //중환자실 소아 수
    val hv33:String, //응급전용 소아중환자실 수
    val hv34:String, //중환자실 심장내과 수
    val hv35:String, //중환자실 음압격리 수
    val hv36:String, //응급전용 입원실 수
    val hv37:String, //응급전용 소아입원실 수
    val hv38:String, //외상전용 입원실 수
    val hv39:String, //외상전용 수술실 수
    val hv40:String, //정신과 폐쇄병동 입원실 수
    val hv41:String, //음압격리 입원실 수
    val hv42:String, //분만실 수
    val hv43:String, //화상전용처치실 수
    val dutyname:String, //기관명
    val dutytel3:String, //응급실 전화
    val HVS01:String, //일반 기준 수
    val HVS02:String, //소아 기준 수
    val HVS03:String, //응급실 음압 격리 병상 기준 수
    val HVS04:String, //응급실 일반 격리 병상 기준 수
    val HVS05:String, //응급전용 중환자실 기준 수
    val HVS06:String, //중환자실 내과 기준 수
    val HVS07:String, //중환자실 외과 기준 수
    val HVS08:String, //중환자실 신생아 기준 수
    val HVS09:String, //중환자실 소아 기준 수
    val HVS10:String, //응급전용 소아중환자실 기준 수
    val HVS11:String, //중환자실 신경과 기준 수
    val HVS12:String, //중환자실 신경외과 기준 수
    val HVS13:String, //중환자실 화상 기준 수
    val HVS14:String, //중환자실 외상 기준 수
    val HVS15:String, //중환자실 심장내과 기준 수 
    val HVS16:String, //중환자실 흉부외과 기준 수
    val HVS17:String, //중환자실 일반 기준 수
    val HVS18:String, //중환자실  음압격리 기준 수
    val HVS19:String, //응급전용 입원실 기준 수
    val HVS20:String, //응급전용 소아입원실 기준 수
    val HVS21:String, //외상전용 입원실 기준 수
    val HVS22:String, //수술실 기준 수
    val HVS23:String, //외상전용 수술실 기준 수
    val HVS24:String, //정신과 폐쇄병동 입원실 기준 수
    val HVS25:String, //음압격리 기준 수
    val HVS26:String, //분만실 기준 수
    val HVS27:String, //CT 기준 수
    val HVS28:String, //MRI 기준 수
    val HVS29:String, //혈관 촬영기 기준 수
    val HVS30:String, //인공호흡기 일반 기준 수
    val HVS31:String, //인공호흡기 조산아 기준 수
    val HVS32:String, //인큐베이터 기준 수
    val HVS33:String, //CRRT 기준 수
    val HVS34:String, //ECMO기준 수
    val HVS35:String, //중심체온조절유도기 기준 수
    val HVS36:String, //화상전용처치실 기준 수
    val HVS37:String, //고압산소치료기 기준 수
    val HVS38:String, //일반 입원실 기준 수
    val HVS46:String, //격리진료구역 음압격리 기준 수
    val HVS47:String, //격리진료구역 일반격리 기준 수
    val HVS48:String, //소아음압격리 기준 수
    val HVS49:String, //소아일반격리 기준 수
    val HVS50:String, //응급전용 중환자실 음압격리 기준 수
    val HVS51:String, //응급전용 중환자시 일반격리 기준 수
    val HVS52:String, //응급전용 입원실 음압격리 기준 수
    val HVS53:String, //응급전용 입원실 일반격리 기준 수
    val HVS54:String, //감염병 전담병상 중환자실 기준 수
    val HVS55:String, //감염병 전담병상 중환자실 내 음압격리 병상 기준 수
    val HVS56:String, //감염 중증 병상 기준 수
    val HVS57:String, //감염 준-중증 병상 기준 수
    val HVS58:String, //감염 중등증 병상 기준 수
    val HVS59:String, //코호트 격리 기준 수
)
