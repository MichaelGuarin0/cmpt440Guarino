import re, os
import itertools
from nltk.tokenize import StanfordTokenizer

from utils import DATA_DIR

def _readDataFile(file):
    with open(file) as data:
        textDoc = data.read()
    textDoc = re.sub('[.!?]\s+', '<eos>', textDoc)
    textDoc = re.sub(',', '', textDoc)
    textDoc = textDoc.split('<eos>')
    return textDoc

def _oneHotEncode(docs):
    docsJoined = '<eod>'.join(docs)


def dtm_builder():
    dataFiles = ['{}/{}'.format(DATA_DIR, file) for file in os.listdir(DATA_DIR) if file.endswith('.txt')]
    allDocs = [_readDataFile(file) for file in dataFiles]
    allDocs_tokenized = [StanfordTokenizer().tokenize(sentence) for doc in allDocs for sentence in doc]
    allVocab = sorted(set(list(itertools.chain.from_iterable(allDocs_tokenized))))

#determine all unique words in all the documents so taht a one hot encoding can be constructed
dtm_builder()
