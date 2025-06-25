// Q1
function evenIdxPositiveSum (myArray) 
{
    var sum =0;
    
    for (i=0; i < myArray.length; i++)
    {
    if (i%2 === 0)
        {
        if (myArray[i]> 0)
            {
            sum = sum + myArray[i];
            }
        }
    }
    return sum;
}
console.log(evenIdxPositiveSum([-2, 3, 1, 24, -8, -9, 10]));
//Q2
function filterer (myArray2) 
{

}