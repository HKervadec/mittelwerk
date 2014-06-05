#include "ShuttleA.h"

typedef void(ShuttleA:: *StatePt)(double, double, double);

void ShuttleA::postStep(double simt, double simdt, double mjd){
	StatePt state_fct[] = { &ShuttleA::STATE_1, 
		&ShuttleA::STATE_2,
		&ShuttleA::STATE_3,
		&ShuttleA::STATE_4,
		&ShuttleA::STATE_5,
		&ShuttleA::STATE_6 };
	(this->*state_fct[m_state])(simt, simdt, mjd);
}

	void ShuttleA::STATE_1(double simt, double simdt, double mjd){
		if( simt > 1  ){
 		SetPodAngle ( 1  , PI / 2  ) 		;
		SetPodAngle ( 2  , PI / 2  ) 		;
		SetThrusterLevel ( th_pod[0] , 1  ) 		;
		SetThrusterLevel ( th_pod[1] , 1  ) 		;
		m_state = 1;
	} 	

	}

	void ShuttleA::STATE_2(double simt, double simdt, double mjd){
		double alti;
	oapiGetAltitude(GetHandle(),&alti);
		if( alti > 250  ){
 		m_state = 2;
	} 	

	}

	void ShuttleA::STATE_3(double simt, double simdt, double mjd){
		double alti;
		double a, b, c;
	oapiGetAltitude(GetHandle(),&alti);
	oapiGetShipAirspeedVector(GetHandle(), &tmp_vector);
	a = tmp_vector.x;
	b = tmp_vector.y;
	c = tmp_vector.z;
	SetThrusterLevel ( th_pod[0] , 0  ) 	;
	SetThrusterLevel ( th_pod[1] , 0  ) 	;
		if( alti > 560  ){
 		m_state = 3;
	} 	

	}

	void ShuttleA::STATE_4(double simt, double simdt, double mjd){
		double alti;
	oapiGetAltitude(GetHandle(),&alti);
	SetThrusterLevel ( th_pod[0] , 0  ) 	;
	SetThrusterLevel ( th_pod[1] , 0  ) 	;
		if( alti < 550  ){
 		m_state = 4;
	} 	

	}

	void ShuttleA::STATE_5(double simt, double simdt, double mjd){
		double alti;
	oapiGetAltitude(GetHandle(),&alti);
	SetThrusterLevel ( th_pod[0] , 0.2  ) 	;
	SetThrusterLevel ( th_pod[1] , 0.2  ) 	;
		if( alti < 500  ){
 		m_state = 5;
	} 	

	}

	void ShuttleA::STATE_6(double simt, double simdt, double mjd){
		double alti;
	oapiGetAltitude(GetHandle(),&alti);
	SetThrusterLevel ( th_pod[0] , 0.7  ) 	;
	SetThrusterLevel ( th_pod[1] , 0.7  ) 	;
		if( alti > 500  ){
 		m_state = 4;
	} 	

	}

