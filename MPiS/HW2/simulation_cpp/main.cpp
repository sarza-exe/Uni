#include <iostream>
#include <random>
#include <fstream>

void zeroArr(int arr[], int n)
{
    for(int i = 0; i < n; i++)
    {
        arr[i] = 0;
    }
}

int main() {

    int k = 50;

    int arrFirstCollision[100][k]; //bn
    int arrEmptyBinsAfterNBalls[100][k]; //un
    int arrCouponCollector[100][k]; //cn
    int arrCouponCollectorBrother[100][k]; //dn

    int nIndex = 0;

    for(int n = 1000; n <=100000; n+=1000)
    {
        for(int repetition = 0; repetition < k; repetition++)
        {
            std::random_device rd;
            std::mt19937 rng(rd()); // Mersenne twister algorithm
            std::uniform_int_distribution<int> bin(0, n - 1);

            int arr[n];
            zeroArr(arr, n);

            int firstCollision = 0;
            int emptyBinsAfterNBalls = 0;
            int couponCollector = 0;
            int couponCollectorBrother = 0;
            int m = 0;
            int emptyBins = n;
            int binsNotHavingTwoBalls = n;

            while(true)
            {
                int index = bin(rng); // random number in range [0,n-1]
                arr[index]+=1;
                m += 1; // a ball was added

                if (m == n) // number of empty bins after putting n balls
                {
                    emptyBinsAfterNBalls = emptyBins;
                }

                if (arr[index] > 2) continue; // optimization

                if (arr[index] == 1) //check if it's the first ball in the bin
                {
                    emptyBins -= 1;
                    if (emptyBins == 0)
                    {
                        couponCollector = m;
                    }
                }
                else if(arr[index] == 2)
                {
                    if (firstCollision == 0) {
                        firstCollision = m;
                    }
                    binsNotHavingTwoBalls -= 1;
                }

                if (binsNotHavingTwoBalls == 0)
                {
                    couponCollectorBrother = m;
                    break;
                }
            }
            arrFirstCollision[nIndex][repetition] = firstCollision;
            arrEmptyBinsAfterNBalls[nIndex][repetition] = emptyBinsAfterNBalls;
            arrCouponCollector[nIndex][repetition] = couponCollector;
            arrCouponCollectorBrother[nIndex][repetition] = couponCollectorBrother;
        }
        nIndex++;
    }


    std::ofstream fileFirstCollision("firstCollision.txt");
    std::ofstream fileEmptyBinsAfterNBalls("emptyBinsAfterNBalls.txt");
    std::ofstream fileCouponCollector("couponCollector.txt");
    std::ofstream fileCouponCollectorBrother("couponCollectorBrother.txt");
    std::ofstream fileNumberOfBallsSinceCn("numberOfBallsSinceCn.txt");

    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < k; j++) {
            fileFirstCollision << arrFirstCollision[i][j] << " ";
            fileEmptyBinsAfterNBalls << arrEmptyBinsAfterNBalls[i][j] << " ";
            fileCouponCollector << arrCouponCollector[i][j] << " ";
            fileCouponCollectorBrother << arrCouponCollectorBrother[i][j] << " ";
            fileNumberOfBallsSinceCn << (arrCouponCollectorBrother[i][j] - arrCouponCollector[i][j]) << " ";
        }
        fileFirstCollision << "\n";
        fileEmptyBinsAfterNBalls << "\n";
        fileCouponCollector << "\n";
        fileCouponCollectorBrother << "\n";
        fileNumberOfBallsSinceCn << "\n";
    }

    fileFirstCollision.close();
    fileEmptyBinsAfterNBalls.close();
    fileCouponCollector.close();
    fileCouponCollectorBrother.close();
    fileNumberOfBallsSinceCn.close();

    std::cout << "Simulation complete" << std::endl;

    return 0;
}
