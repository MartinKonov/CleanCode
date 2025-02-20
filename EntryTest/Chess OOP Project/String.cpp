#include "String.h"

void String::append(const String& other) {
    if (currentSize == capacity) {
        expandCapacity();
    }

    if (capacity - currentSize >= other.currentSize) {
        size_t tempIndex = currentSize;
        for (size_t i = 0; i < other.currentSize; i++) {
            charArray[tempIndex++] = other.charArray[i];
        }
        currentSize += other.currentSize;
        charArray[currentSize] = '\0';
    } else {
        expandCapacity();
        append(other);
    }
}

void String::copy(const String& other) {
    capacity = other.capacity;
    currentSize = other.currentSize;
    charArray = new char[capacity + 1];

    for (size_t i = 0; i < currentSize; i++) {
        charArray[i] = other.charArray[i];
    }
    charArray[currentSize] = '\0';
}

void String::expandCapacity() {
    capacity *= 2;
    char* tempArray = new char[capacity + 1];

    for (size_t i = 0; i < currentSize; i++) {
        tempArray[i] = charArray[i];
    }

    delete[] charArray;
    charArray = tempArray;
}

void String::freeMemory() {
    delete[] charArray;
}

// Constructors & Destructor
String::String() : charArray(nullptr), currentSize(0), capacity(8) {}

String::String(const String& other) {
    copy(other);
}

String::String(const char* inputString) {
    currentSize = strlen(inputString);
    capacity = currentSize * 2;
    charArray = new char[capacity + 1];
    strcpy_s(charArray, capacity + 1, inputString);
    charArray[currentSize] = '\0';
}

String::~String() {
    freeMemory();
}

// Operators
String& String::operator=(const String& other) {
    if (this != &other) {
        freeMemory();
        copy(other);
    }
    return *this;
}

String& String::operator+(const String& other) {
    append(other);
    return *this;
}

String& String::operator+=(const String& other) {
    append(other);
    return *this;
}

char String::operator[](size_t index) const {
    if (index >= currentSize) {
        exit(-1);
    }
    return charArray[index];
}

bool String::operator==(const String& other) const {
    return strcmp(charArray, other.charArray) == 0;
}

size_t String::length() const {
    return currentSize;
}

bool String::isEmpty() const {
    return currentSize == 0;
}

bool String::contains(const String& substring) const {
    for (size_t i = 0; i < currentSize - substring.currentSize + 1; i++) {
        bool matchFound = true;
        for (size_t j = 0; j < substring.currentSize; j++) {
            if (charArray[i + j] != substring.charArray[j]) {
                matchFound = false;
                break;
            }
        }
        if (matchFound) {
            return true;
        }
    }
    return false;
}

void String::appendCharacter(char character) {
    if (currentSize == capacity - 1) {
        expandCapacity();
    }
    charArray[currentSize++] = character;
    charArray[currentSize] = '\0';
}

void String::removeLastCharacter() {
    if (currentSize > 0) {
        charArray[--currentSize] = '\0';
    }
}

// Stream Operators
std::ostream& operator<<(std::ostream& os, const String& stringObj) {
    for (size_t i = 0; i < stringObj.currentSize; i++) {
        os << stringObj.charArray[i];
    }
    return os;
}

std::istream& operator>>(std::istream& is, String& stringObj) {
    char buffer[128];
    is.getline(buffer, 128);
    stringObj = String(buffer);
    return is;
}
