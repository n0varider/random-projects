#include <iostream>
using namespace std;

int main() {
	int selection;
	cout << "Enter the number corresponding to the calculation." << endl;
	cout << "1: Bennett ATK Buff" << endl;
	cout << "2: Diona Shield HP" << endl;
	cout << "3: Kazuha EM Buff" << endl;
	cout << ">> Selection: ";
	cin >> selection; // Assume proper input
	switch(selection) {
		case 1:
			int base_atk;
			cout << "Enter Bennett's Base ATK: ";
			cin >> base_atk;
			base_atk = (1.19 * base_atk) + (0.2 * base_atk); // Talent 13, C1
			cout << ">> Bennett's ATK Buff is ";
			cout << base_atk;
			break;
		case 2:
			int hp;
			cout << "Enter Diona's HP: ";
			cin >> hp;
			hp = (14.4 / 100) * hp + 1773; // Talent 12
			cout << ">> Base Absorption: ";
			cout << hp << endl;
			hp = hp * 1.75 * 1.15; // Hold Multiplier, C2
			cout << ">> Hold Skill + C1 Absorption: ";
			cout << hp;
			break;
		case 3:
			int em;
			cout << "Enter Elemental Mastery: ";
			cin >> em;
			em = 0.04 * em;
			cout << ">> The Elemental Damage Buff is ";
			cout << em << "%";
			break;
		default:
			cout << "Invalid input" << endl;
			return 1;
		}
	return 0;
}
