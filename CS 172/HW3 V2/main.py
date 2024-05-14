#Author: Sam Turay Jr (st3387)

from questions import MultipleChoice, ShortAnswer, FillInTheBlank

def answerQuestion(question):
    print(question.displayForTest())
    return input("Enter your answer: ")

def gradeQuestion(question, userAnswer):
    if userAnswer.lower() == question.getCorrectAnswer().lower():
        return question.getPoints()
    return 0

# Main script
def main():
    # Creating question objects
    q1 = MultipleChoice("Which animal is the Drexel mascot?", ["Dragon", "Tiger", "Bulldog"], "a", 5)
    q2 = ShortAnswer("What are the Drexel colors?", 13, "blue and gold", 5)
    q3 = FillInTheBlank("The name of Drexel University's founder is ____ J. Drexel.", "Anthony", 5)

    # Displaying questions to the user
    questions = [q1, q2, q3]
    total_points = 0
    for question in questions:
        user_answer = answerQuestion(question)
        total_points += gradeQuestion(question, user_answer)

    print(f"You earned: {total_points} points")

if __name__ == "__main__":
    main()
