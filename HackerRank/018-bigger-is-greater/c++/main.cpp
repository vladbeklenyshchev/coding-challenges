#include <string>
#include <iostream>

using namespace std;

// Complete the biggerIsGreater function below.
string biggerIsGreater(string w) {

	return "string from biggerIsGreater function.";
}

int main()
{
	//ofstream fout(getenv("OUTPUT_PATH"));

	int T;
	cin >> T;
	cin.ignore(numeric_limits<streamsize>::max(), '\n');

	for (int T_itr = 0; T_itr < T; T_itr++) {
		string w;
		getline(cin, w);

		string result = biggerIsGreater(w);

		cout << result << "\n";
	}

	//fout.close();

	return 0;
}
