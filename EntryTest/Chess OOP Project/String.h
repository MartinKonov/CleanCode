#pragma once
#include <iostream>
#include <cstring>


class String {
private:
    char* charArray;
    size_t currentSize;
    size_t capacity;

    void append(const String& other);
    void copy(const String& other);
    void expandCapacity();
    void freeMemory();

public:
    String();
    String(const String& other);
    String(const char* inputString);
    ~String();

    String& operator=(const String& other);
    
    size_t length() const;
    bool isEmpty() const;
    bool contains(const String& substring) const;

    void appendCharacter(char character);
    void removeLastCharacter();

    String& operator+(const String& other);
    String& operator+=(const String& other);
    char operator[](size_t index) const;
    bool operator==(const String& other) const;

    friend std::ostream& operator<<(std::ostream& os, const String& stringObj);
    friend std::istream& operator>>(std::istream& is, String& stringObj);
};
