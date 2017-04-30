import re, os
from nltk.tokenize import StanfordTokenizer

from utils import DATA_DIR

def _readDataFile(file):
    with open(file) as data:
        textData = data.read()
    textData = re.sub('[.!?]\s+', '<eos>', textData)
    textData = re.sub(',', '', textData)
    return textData

def dtm_builder():
    dataFiles = ['{}/{}'.format(DATA_DIR, file) for file in os.listdir(DATA_DIR) if file.endswith('.txt')]
    textData_allDocs = [_readDataFile(file) for file in dataFiles]
    textData_sentences = [file.split('<eos>') for file in textData_allDocs]
    print(textData_sentences)
    #tokenizer =[ MosesTokenizer() for sentence in textData_sentences]

#determine all unique words in all the documents so taht a one hot encoding can be constructed
dtm_builder()
