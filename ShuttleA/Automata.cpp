#include "ShuttleA.h"

typedef void(ShuttleA:: *StatePt)(double, double, double);

void ShuttleA::postStep(double simt, double simdt, double mjd){
	StatePt state_fct[] = { &ShuttleA::STATE_1, 
		&ShuttleA::STATE_2,
		&ShuttleA::STATE_3 };
	(this->*state_fct[m_state])(simt, simdt, mjd);
}

	void ShuttleA::STATE_1(double simt, double simdt, double mjd){
		double alti_start;
		double a, b, c;
	oapiGetAltitude(GetHandle(),&alti_start);
	oapiGetShipAirspeedVector(GetHandle(), &tmp_vector);
	a = tmp_vector.x;
	b = tmp_vector.y;
	c = tmp_vector.z;
		if( a == 0  ){
 		SetThrusterLevel ( th_hover[0] , 1  ) 		;
		SetThrusterLevel ( th_hover[1] , 1  ) 		;
		SetPodAngle ( 1  , PI / 2  ) 		;
		SetPodAngle ( 3  , PI / 2  ) 		;
		SetThrusterLevel ( th_pod[0] , 1  ) 		;
		SetThrusterLevel ( th_pod[1] , 1  ) 		;
	} 	

		if( alti_start > 100  ){
 		m_state = 1;
	} 	

	}

	void ShuttleA::STATE_2(double simt, double simdt, double mjd){
		double alti_no_pod;
	oapiGetAltitude(GetHandle(),&alti_no_pod);
	SetThrusterLevel ( th_main[0] , 1  ) 	;
	SetThrusterLevel ( th_main[1] , 1  ) 	;
		if( alti_no_pod > 1500  ){
 		m_state = 2;
	} 	

	}

	void ShuttleA::STATE_3(double simt, double simdt, double mjd){
	SetThrusterLevel ( th_pod[0] , 0  ) 	;
	SetThrusterLevel ( th_pod[1] , 0  ) 	;
	}

