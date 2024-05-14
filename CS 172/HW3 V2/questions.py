#Author: Sam Turay Jr (st3387)

from abc import ABC, abstractmethod

class Question(ABC):
    def __init__(self, prompt, correct_answer="", points=0):
        self.__prompt = prompt
        self.__correct_answer = correct_answer
        self.__points = points

    def getPrompt(self):
        return self.__prompt

    def setPrompt(self, prompt):
        self.__prompt = prompt

    def getCorrectAnswer(self):
        return self.__correct_answer

    def setCorrectAnswer(self, correct_answer):
        self.__correct_answer = correct_answer

    def getPoints(self):
        return self.__points

    def setPoints(self, points):
        self.__points = points

    @abstractmethod
    def displayForTest(self):
        pass

    def __str__(self):
        return f"Prompt: {self.__prompt}\nCorrect Answer: {self.__correct_answer}\nPoints: {self.__points}\n"


class MultipleChoice(Question):
    def __init__(self, prompt, choices, correct_answer="", points=0):
        super().__init__(prompt, correct_answer, points)
        self.__choices = choices
    def setCorrectAnswer(self, correctAnswer):
        self.__correct_answer = correctAnswer

    def addChoice(self, choice):
        self.__choices.append(choice)

    def updateChoice(self, index, choice):
        self.__choices[index] = choice

    def getChoices(self):
        return self.__choices

    def displayForTest(self):
        display = self.getPrompt() + "\n"


       # for i, choice in enumerate(self.getChoices()):
       #     display += f"{chr(97 + i)}. {choice}\n"
        for choice in self.getChoices():
            display += choice + "\n"


        return display

    def __str__(self):
        return super().__str__() + f"\nChoices: {self.__choices}"


class ShortAnswer(Question):
    def __init__(self, prompt, length, correct_answer="", points=0):
        super().__init__(prompt, correct_answer, points)
        self.__length = length

    def setCorrectAnswer(self, correct_answer):
        self.__correct_answer = correct_answer
    
    def getLength(self):
        return self.__length

    def setLength(self, length):
        self.__length = length

    def displayForTest(self):
        return f"{self.getPrompt()} (up to {self.getLength()} characters)\n"

    def __str__(self):
        return f"Prompt: {self.get_prompt()}\nCorrect Answer: {self.get_correct_answer()}\nPoints: {self.get_points()}\nCharacter limit: {self.get_length()}\n"
       # return super().__str__() + f"Character limit: {self.__length}\n"


class FillInTheBlank(Question):
    def displayForTest(self):
        return f"Fill in the blank:\n{self.get_prompt()}\n"
        #return f"Fill in the blank:\n{self.getPrompt()}\n"
    
    def __str__(self):
        return super().__str__()

