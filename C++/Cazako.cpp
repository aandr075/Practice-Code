#include <cstdio>
#include <cstdarg> //used with va_ variables; allows variable number of parameters
#include <iostream> //allows cout
#include <string> //imports string library
#include <exception>
#include <cmath>
using namespace std;

class Hero {
	string _alterEgo;
	string _power;
	Hero() {};
protected:
	Hero(const string alt, const string pow) : _alterEgo(alt), _power(pow) {}
public:
	void iam() { printf("%s has the power of %s\n", _alterEgo.c_str(), _power.c_str()); }
	const string & alterEgo() const { return _alterEgo; }
};


//base class
class Cazako {
	string _name;
	string _phrase;
	string _series;
	//private constructor prevents default construction
	Cazako() {}

	friend class ProjectBETA; //allows derived class to have access to private members [not conventional]
protected:
	//protected constructor used by deribed classes
	Cazako(const string & n, const string & p, const string & s)
		: _name(n), _phrase(p), _series(s) {};

public:
	virtual ~Cazako() {}; // when declaring anything virtual, you must declare a virtual destructor
	virtual void say() const; //virtual allows derived class's overloaded version to be prioritized
	const string & name() const { return _name; }
	const string & phrase() const { return _phrase; }
	const string & series() const { return _series; }
};

void Cazako::say() const {
	printf("%s of %s says:\n\"%s\"\n", _name.c_str(), _series.c_str(), _phrase.c_str());
}

//derived classes
class Khaosklub : public Cazako {// : base class => inherits from base class
	string team;
	string weapon;
public:
	Khaosklub(string n, string p, string t, string w) : Cazako(n, p, "Khaos Klub"), team(t), weapon(w) {};
	void attack() { printf("%s attacks with a %s\n",name().c_str(),weapon.c_str()); }
	void affil() { printf("%s belongs to team %s\n", Cazako::name().c_str(), team.c_str()); } //can use base class::method

};

class ProjectBETA : public Cazako, public Hero{// : base class => inherits from base class
	string faction;
public:
	ProjectBETA(string n, string a, string p, string f, string pow) 
		: Cazako(n, p, "Project B.E.T.A."), Hero(a,pow), faction(f) {};
	void fact() { printf("%s is part of %s\n", alterEgo().c_str(), faction.c_str()); }
	void identity() { printf("%s's identiy is %s\n", alterEgo().c_str(), _name.c_str()); } //can directly access base class members due to friendship declaration
	void say() const { printf("%s of %s says:\n\"%s\"\n", alterEgo().c_str(), _series.c_str(), _phrase.c_str()); }
};

int main(int argc, char ** argv) {
	Khaosklub erik("Erik", "Remember the RED", "Khaos Klub", "Red Katana");
	ProjectBETA plasmaBoy("Alex Yoshima", "Plasma Boy", "Eat Plasma!", "Police Force", "Plasma Energy");

	erik.say();
	erik.affil();
	erik.attack();
	puts("");
	plasmaBoy.say();	
	plasmaBoy.fact();	
	plasmaBoy.iam();
	plasmaBoy.identity();
	puts("");

	//polymorphic pointer experiment
	Cazako *characters[] = { &erik, &plasmaBoy };
	puts("for loop over derived objects");

	for (Cazako * x : characters) {
		x->say(); //virtual allows overloaded versions of functions
	}


	return 0;
}

