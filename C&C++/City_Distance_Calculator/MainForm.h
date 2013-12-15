/**
 * Main GUI file and implemention of GUI
 * Contains all back-end implementations
 * 
 * @author Ollie, George, JJ
 **/

#pragma once
#include "List.h"
#include <cstdlib>
#include <stdlib.h>
#include <fstream>
#include <sstream>
#include <math.h>
#include <msclr\marshal_cppstd.h>
using namespace std;
namespace test {
    
	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;
    
	List city_list;
    List temp_list;
	/// <summary>
	/// Summary for MainForm
	/// </summary>
	public ref class MainForm : public System::Windows::Forms::Form
	{
	public:
		MainForm(void)
		{
			InitializeComponent();
			loadFile();
		}
        
	protected:
		~MainForm()
		{
			if (components)
			{
				delete components;
			}
		}
        
	private: System::Windows::Forms::Label^  distanceCalculatorTitleLabel;
	private: System::Windows::Forms::Label^  maintenanceTitleLabel;
	private: System::Windows::Forms::Label^  cdcEntryFirstCityNameLabel;
        
	private: System::Windows::Forms::Label^  maintenanceCityNameLabel;
	private: System::Windows::Forms::Label^  cdcEntryFirstCityCountryLabel;
        
	private: System::Windows::Forms::Label^  maintenanceEntryCountryLabel;
	private: System::Windows::Forms::TextBox^  maintenanceCityNameTextField;
	private: System::Windows::Forms::TextBox^  cdcFirstCityNameTextEntry;
	private: System::Windows::Forms::TextBox^  cdcFirstCityCountryTextEntry;
        
        
	private: System::Windows::Forms::TextBox^  maintenanceCityCountryTextEntry;
	private: System::Windows::Forms::Label^  titleLabel;
	private: System::Windows::Forms::TextBox^  textBox1;
	private: System::Windows::Forms::Label^  maintenanceCityLatitudeLabel;
	private: System::Windows::Forms::TextBox^  maintenanceCityLatitudeTextEntry;
        
	private: System::Windows::Forms::Label^  maintenanceCityLongditudeLabel;
	private: System::Windows::Forms::TextBox^  maintenanceCityLongditudeTextEntry;
	private: System::Windows::Forms::Button^  addCityButton;
	private: System::Windows::Forms::Button^  removeCityButton;
	private: System::Windows::Forms::Button^  maintenanceEditButton;
	private: System::Windows::Forms::Button^  cdcCalculateButton;
	private: System::Windows::Forms::Label^  cdcSecondCityNameLabel;
	private: System::Windows::Forms::Label^  cdcSecondCityCountryLabel;
	private: System::Windows::Forms::TextBox^  cdcSecondCityNameTextEntry;
	private: System::Windows::Forms::TextBox^  cdcSecondCityCountryTextEntry;
	private: System::Windows::Forms::TextBox^  textBox2;
	private: System::Windows::Forms::TextBox^  textBox3;
	private: System::Windows::Forms::TextBox^  outputTextBox;
	private: System::Windows::Forms::Label^  outputLabel;
	private: System::Windows::Forms::Button^  searchButton;
	private: System::Windows::Forms::Button^  tenClosestCitiesButton;
	private: System::Windows::Forms::Button^  displayAllButton;

        
        
        
	protected:
        
	protected:
        
	protected:
        
	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;
        
#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->distanceCalculatorTitleLabel = (gcnew System::Windows::Forms::Label());
			this->maintenanceTitleLabel = (gcnew System::Windows::Forms::Label());
			this->cdcEntryFirstCityNameLabel = (gcnew System::Windows::Forms::Label());
			this->maintenanceCityNameLabel = (gcnew System::Windows::Forms::Label());
			this->cdcEntryFirstCityCountryLabel = (gcnew System::Windows::Forms::Label());
			this->maintenanceEntryCountryLabel = (gcnew System::Windows::Forms::Label());
			this->maintenanceCityNameTextField = (gcnew System::Windows::Forms::TextBox());
			this->cdcFirstCityNameTextEntry = (gcnew System::Windows::Forms::TextBox());
			this->cdcFirstCityCountryTextEntry = (gcnew System::Windows::Forms::TextBox());
			this->maintenanceCityCountryTextEntry = (gcnew System::Windows::Forms::TextBox());
			this->titleLabel = (gcnew System::Windows::Forms::Label());
			this->textBox1 = (gcnew System::Windows::Forms::TextBox());
			this->maintenanceCityLatitudeLabel = (gcnew System::Windows::Forms::Label());
			this->maintenanceCityLatitudeTextEntry = (gcnew System::Windows::Forms::TextBox());
			this->maintenanceCityLongditudeLabel = (gcnew System::Windows::Forms::Label());
			this->maintenanceCityLongditudeTextEntry = (gcnew System::Windows::Forms::TextBox());
			this->addCityButton = (gcnew System::Windows::Forms::Button());
			this->removeCityButton = (gcnew System::Windows::Forms::Button());
			this->maintenanceEditButton = (gcnew System::Windows::Forms::Button());
			this->cdcCalculateButton = (gcnew System::Windows::Forms::Button());
			this->cdcSecondCityNameLabel = (gcnew System::Windows::Forms::Label());
			this->cdcSecondCityCountryLabel = (gcnew System::Windows::Forms::Label());
			this->cdcSecondCityNameTextEntry = (gcnew System::Windows::Forms::TextBox());
			this->cdcSecondCityCountryTextEntry = (gcnew System::Windows::Forms::TextBox());
			this->textBox2 = (gcnew System::Windows::Forms::TextBox());
			this->textBox3 = (gcnew System::Windows::Forms::TextBox());
			this->outputTextBox = (gcnew System::Windows::Forms::TextBox());
			this->outputLabel = (gcnew System::Windows::Forms::Label());
			this->searchButton = (gcnew System::Windows::Forms::Button());
			this->tenClosestCitiesButton = (gcnew System::Windows::Forms::Button());
			this->displayAllButton = (gcnew System::Windows::Forms::Button());
			this->SuspendLayout();
			// 
			// distanceCalculatorTitleLabel
			// 
			this->distanceCalculatorTitleLabel->AutoSize = true;
			this->distanceCalculatorTitleLabel->Font = (gcnew System::Drawing::Font(L"Segoe UI", 10, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(0)));
			this->distanceCalculatorTitleLabel->Location = System::Drawing::Point(73, 72);
			this->distanceCalculatorTitleLabel->Name = L"distanceCalculatorTitleLabel";
			this->distanceCalculatorTitleLabel->Size = System::Drawing::Size(134, 19);
			this->distanceCalculatorTitleLabel->TabIndex = 1;
			this->distanceCalculatorTitleLabel->Text = L"Calculate Distance:";
			// 
			// maintenanceTitleLabel
			// 
			this->maintenanceTitleLabel->AutoSize = true;
			this->maintenanceTitleLabel->Font = (gcnew System::Drawing::Font(L"Segoe UI", 10, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(0)));
			this->maintenanceTitleLabel->Location = System::Drawing::Point(381, 72);
			this->maintenanceTitleLabel->Name = L"maintenanceTitleLabel";
			this->maintenanceTitleLabel->Size = System::Drawing::Size(128, 19);
			this->maintenanceTitleLabel->TabIndex = 2;
			this->maintenanceTitleLabel->Text = L"City Maintenance:";
			// 
			// cdcEntryFirstCityNameLabel
			// 
			this->cdcEntryFirstCityNameLabel->AutoSize = true;
			this->cdcEntryFirstCityNameLabel->Font = (gcnew System::Drawing::Font(L"Segoe UI", 8.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(0)));
			this->cdcEntryFirstCityNameLabel->Location = System::Drawing::Point(25, 112);
			this->cdcEntryFirstCityNameLabel->Name = L"cdcEntryFirstCityNameLabel";
			this->cdcEntryFirstCityNameLabel->Size = System::Drawing::Size(86, 13);
			this->cdcEntryFirstCityNameLabel->TabIndex = 3;
			this->cdcEntryFirstCityNameLabel->Text = L"First City Name:";
			// 
			// maintenanceCityNameLabel
			// 
			this->maintenanceCityNameLabel->AutoSize = true;
			this->maintenanceCityNameLabel->Font = (gcnew System::Drawing::Font(L"Segoe UI", 8.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(0)));
			this->maintenanceCityNameLabel->Location = System::Drawing::Point(341, 115);
			this->maintenanceCityNameLabel->Name = L"maintenanceCityNameLabel";
			this->maintenanceCityNameLabel->Size = System::Drawing::Size(61, 13);
			this->maintenanceCityNameLabel->TabIndex = 4;
			this->maintenanceCityNameLabel->Text = L"City Name:";
			// 
			// cdcEntryFirstCityCountryLabel
			// 
			this->cdcEntryFirstCityCountryLabel->AutoSize = true;
			this->cdcEntryFirstCityCountryLabel->Font = (gcnew System::Drawing::Font(L"Segoe UI", 8.25F, System::Drawing::FontStyle::Regular, 
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->cdcEntryFirstCityCountryLabel->Location = System::Drawing::Point(25, 142);
			this->cdcEntryFirstCityCountryLabel->Name = L"cdcEntryFirstCityCountryLabel";
			this->cdcEntryFirstCityCountryLabel->Size = System::Drawing::Size(98, 13);
			this->cdcEntryFirstCityCountryLabel->TabIndex = 5;
			this->cdcEntryFirstCityCountryLabel->Text = L"First City Country:";
			// 
			// maintenanceEntryCountryLabel
			// 
			this->maintenanceEntryCountryLabel->AutoSize = true;
			this->maintenanceEntryCountryLabel->Font = (gcnew System::Drawing::Font(L"Segoe UI", 8.25F, System::Drawing::FontStyle::Regular, 
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->maintenanceEntryCountryLabel->Location = System::Drawing::Point(341, 142);
			this->maintenanceEntryCountryLabel->Name = L"maintenanceEntryCountryLabel";
			this->maintenanceEntryCountryLabel->Size = System::Drawing::Size(73, 13);
			this->maintenanceEntryCountryLabel->TabIndex = 6;
			this->maintenanceEntryCountryLabel->Text = L"City Country:";
			// 
			// maintenanceCityNameTextField
			// 
			this->maintenanceCityNameTextField->Font = (gcnew System::Drawing::Font(L"Segoe UI", 8.25F, System::Drawing::FontStyle::Regular, 
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->maintenanceCityNameTextField->Location = System::Drawing::Point(438, 112);
			this->maintenanceCityNameTextField->Name = L"maintenanceCityNameTextField";
			this->maintenanceCityNameTextField->Size = System::Drawing::Size(100, 22);
			this->maintenanceCityNameTextField->TabIndex = 6;
			// 
			// cdcFirstCityNameTextEntry
			// 
			this->cdcFirstCityNameTextEntry->Location = System::Drawing::Point(144, 109);
			this->cdcFirstCityNameTextEntry->Name = L"cdcFirstCityNameTextEntry";
			this->cdcFirstCityNameTextEntry->Size = System::Drawing::Size(100, 22);
			this->cdcFirstCityNameTextEntry->TabIndex = 1;
			// 
			// cdcFirstCityCountryTextEntry
			// 
			this->cdcFirstCityCountryTextEntry->Location = System::Drawing::Point(144, 139);
			this->cdcFirstCityCountryTextEntry->Name = L"cdcFirstCityCountryTextEntry";
			this->cdcFirstCityCountryTextEntry->Size = System::Drawing::Size(100, 22);
			this->cdcFirstCityCountryTextEntry->TabIndex = 2;
			// 
			// maintenanceCityCountryTextEntry
			// 
			this->maintenanceCityCountryTextEntry->Font = (gcnew System::Drawing::Font(L"Segoe UI", 8.25F, System::Drawing::FontStyle::Regular, 
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->maintenanceCityCountryTextEntry->Location = System::Drawing::Point(438, 142);
			this->maintenanceCityCountryTextEntry->Name = L"maintenanceCityCountryTextEntry";
			this->maintenanceCityCountryTextEntry->Size = System::Drawing::Size(100, 22);
			this->maintenanceCityCountryTextEntry->TabIndex = 7;
			// 
			// titleLabel
			// 
			this->titleLabel->AutoSize = true;
			this->titleLabel->BackColor = System::Drawing::SystemColors::Highlight;
			this->titleLabel->Font = (gcnew System::Drawing::Font(L"Segoe UI", 14, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(0)));
			this->titleLabel->ForeColor = System::Drawing::SystemColors::ButtonHighlight;
			this->titleLabel->Location = System::Drawing::Point(186, 9);
			this->titleLabel->Name = L"titleLabel";
			this->titleLabel->Size = System::Drawing::Size(226, 25);
			this->titleLabel->TabIndex = 0;
			this->titleLabel->Text = L"City Distance Calculator:";
			// 
			// textBox1
			// 
			this->textBox1->BackColor = System::Drawing::SystemColors::MenuHighlight;
			this->textBox1->Location = System::Drawing::Point(-7, -5);
			this->textBox1->Multiline = true;
			this->textBox1->Name = L"textBox1";
			this->textBox1->Size = System::Drawing::Size(602, 52);
			this->textBox1->TabIndex = 11;
			this->textBox1->WordWrap = false;
			// 
			// maintenanceCityLatitudeLabel
			// 
			this->maintenanceCityLatitudeLabel->AutoSize = true;
			this->maintenanceCityLatitudeLabel->Location = System::Drawing::Point(340, 175);
			this->maintenanceCityLatitudeLabel->Name = L"maintenanceCityLatitudeLabel";
			this->maintenanceCityLatitudeLabel->Size = System::Drawing::Size(74, 13);
			this->maintenanceCityLatitudeLabel->TabIndex = 13;
			this->maintenanceCityLatitudeLabel->Text = L"City Latitude:";
			// 
			// maintenanceCityLatitudeTextEntry
			// 
			this->maintenanceCityLatitudeTextEntry->Font = (gcnew System::Drawing::Font(L"Segoe UI", 8.25F, System::Drawing::FontStyle::Regular, 
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->maintenanceCityLatitudeTextEntry->Location = System::Drawing::Point(438, 172);
			this->maintenanceCityLatitudeTextEntry->Name = L"maintenanceCityLatitudeTextEntry";
			this->maintenanceCityLatitudeTextEntry->Size = System::Drawing::Size(100, 22);
			this->maintenanceCityLatitudeTextEntry->TabIndex = 8;
			// 
			// maintenanceCityLongditudeLabel
			// 
			this->maintenanceCityLongditudeLabel->AutoSize = true;
			this->maintenanceCityLongditudeLabel->Location = System::Drawing::Point(340, 208);
			this->maintenanceCityLongditudeLabel->Name = L"maintenanceCityLongditudeLabel";
			this->maintenanceCityLongditudeLabel->Size = System::Drawing::Size(92, 13);
			this->maintenanceCityLongditudeLabel->TabIndex = 14;
			this->maintenanceCityLongditudeLabel->Text = L"City Longditude:";
			// 
			// maintenanceCityLongditudeTextEntry
			// 
			this->maintenanceCityLongditudeTextEntry->Font = (gcnew System::Drawing::Font(L"Segoe UI", 8.25F, System::Drawing::FontStyle::Regular, 
				System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->maintenanceCityLongditudeTextEntry->Location = System::Drawing::Point(438, 205);
			this->maintenanceCityLongditudeTextEntry->Name = L"maintenanceCityLongditudeTextEntry";
			this->maintenanceCityLongditudeTextEntry->Size = System::Drawing::Size(100, 22);
			this->maintenanceCityLongditudeTextEntry->TabIndex = 9;
			// 
			// addCityButton
			// 
			this->addCityButton->Location = System::Drawing::Point(321, 250);
			this->addCityButton->Name = L"addCityButton";
			this->addCityButton->Size = System::Drawing::Size(75, 34);
			this->addCityButton->TabIndex = 10;
			this->addCityButton->Text = L"Add";
			this->addCityButton->UseVisualStyleBackColor = true;
			this->addCityButton->Click += gcnew System::EventHandler(this, &MainForm::addCityButton_Click);
			// 
			// removeCityButton
			// 
			this->removeCityButton->Location = System::Drawing::Point(402, 250);
			this->removeCityButton->Name = L"removeCityButton";
			this->removeCityButton->Size = System::Drawing::Size(75, 34);
			this->removeCityButton->TabIndex = 11;
			this->removeCityButton->Text = L"Remove";
			this->removeCityButton->UseVisualStyleBackColor = true;
			this->removeCityButton->Click += gcnew System::EventHandler(this, &MainForm::removeCityButton_Click);
			// 
			// maintenanceEditButton
			// 
			this->maintenanceEditButton->Location = System::Drawing::Point(483, 250);
			this->maintenanceEditButton->Name = L"maintenanceEditButton";
			this->maintenanceEditButton->Size = System::Drawing::Size(75, 34);
			this->maintenanceEditButton->TabIndex = 12;
			this->maintenanceEditButton->Text = L"Edit";
			this->maintenanceEditButton->UseVisualStyleBackColor = true;
			this->maintenanceEditButton->Click += gcnew System::EventHandler(this, &MainForm::maintenanceEditButton_Click);
			// 
			// cdcCalculateButton
			// 
			this->cdcCalculateButton->Location = System::Drawing::Point(169, 259);
			this->cdcCalculateButton->Name = L"cdcCalculateButton";
			this->cdcCalculateButton->Size = System::Drawing::Size(75, 34);
			this->cdcCalculateButton->TabIndex = 5;
			this->cdcCalculateButton->Text = L"Calculate!";
			this->cdcCalculateButton->UseVisualStyleBackColor = true;
			this->cdcCalculateButton->Click += gcnew System::EventHandler(this, &MainForm::cdcCalculateButton_Click);
			// 
			// cdcSecondCityNameLabel
			// 
			this->cdcSecondCityNameLabel->AutoSize = true;
			this->cdcSecondCityNameLabel->Location = System::Drawing::Point(25, 184);
			this->cdcSecondCityNameLabel->Name = L"cdcSecondCityNameLabel";
			this->cdcSecondCityNameLabel->Size = System::Drawing::Size(102, 13);
			this->cdcSecondCityNameLabel->TabIndex = 20;
			this->cdcSecondCityNameLabel->Text = L"Second City Name:";
			// 
			// cdcSecondCityCountryLabel
			// 
			this->cdcSecondCityCountryLabel->AutoSize = true;
			this->cdcSecondCityCountryLabel->Location = System::Drawing::Point(25, 216);
			this->cdcSecondCityCountryLabel->Name = L"cdcSecondCityCountryLabel";
			this->cdcSecondCityCountryLabel->Size = System::Drawing::Size(114, 13);
			this->cdcSecondCityCountryLabel->TabIndex = 21;
			this->cdcSecondCityCountryLabel->Text = L"Second City Country:";
			// 
			// cdcSecondCityNameTextEntry
			// 
			this->cdcSecondCityNameTextEntry->Location = System::Drawing::Point(144, 180);
			this->cdcSecondCityNameTextEntry->Name = L"cdcSecondCityNameTextEntry";
			this->cdcSecondCityNameTextEntry->Size = System::Drawing::Size(100, 22);
			this->cdcSecondCityNameTextEntry->TabIndex = 3;
			// 
			// cdcSecondCityCountryTextEntry
			// 
			this->cdcSecondCityCountryTextEntry->Location = System::Drawing::Point(144, 213);
			this->cdcSecondCityCountryTextEntry->Name = L"cdcSecondCityCountryTextEntry";
			this->cdcSecondCityCountryTextEntry->Size = System::Drawing::Size(100, 22);
			this->cdcSecondCityCountryTextEntry->TabIndex = 4;
			// 
			// textBox2
			// 
			this->textBox2->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->textBox2->Location = System::Drawing::Point(-7, 40);
			this->textBox2->Multiline = true;
			this->textBox2->Name = L"textBox2";
			this->textBox2->Size = System::Drawing::Size(302, 300);
			this->textBox2->TabIndex = 24;
			// 
			// textBox3
			// 
			this->textBox3->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->textBox3->Location = System::Drawing::Point(293, 40);
			this->textBox3->Multiline = true;
			this->textBox3->Name = L"textBox3";
			this->textBox3->Size = System::Drawing::Size(302, 300);
			this->textBox3->TabIndex = 25;
			// 
			// outputTextBox
			// 
			this->outputTextBox->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->outputTextBox->Location = System::Drawing::Point(-7, 338);
			this->outputTextBox->Multiline = true;
			this->outputTextBox->Name = L"outputTextBox";
			this->outputTextBox->ScrollBars = System::Windows::Forms::ScrollBars::Vertical;
			this->outputTextBox->Size = System::Drawing::Size(593, 163);
			this->outputTextBox->TabIndex = 26;
			// 
			// outputLabel
			// 
			this->outputLabel->AutoSize = true;
			this->outputLabel->Font = (gcnew System::Drawing::Font(L"Segoe UI", 10, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(0)));
			this->outputLabel->Location = System::Drawing::Point(12, 316);
			this->outputLabel->Name = L"outputLabel";
			this->outputLabel->Size = System::Drawing::Size(59, 19);
			this->outputLabel->TabIndex = 27;
			this->outputLabel->Text = L"Output:";
			// 
			// searchButton
			// 
			this->searchButton->Location = System::Drawing::Point(360, 290);
			this->searchButton->Name = L"searchButton";
			this->searchButton->Size = System::Drawing::Size(75, 34);
			this->searchButton->TabIndex = 28;
			this->searchButton->Text = L"Search";
			this->searchButton->UseVisualStyleBackColor = true;
			this->searchButton->Click += gcnew System::EventHandler(this, &MainForm::searchButton_Click);
			// 
			// tenClosestCitiesButton
			// 
			this->tenClosestCitiesButton->Location = System::Drawing::Point(88, 259);
			this->tenClosestCitiesButton->Name = L"tenClosestCitiesButton";
			this->tenClosestCitiesButton->Size = System::Drawing::Size(75, 34);
			this->tenClosestCitiesButton->TabIndex = 29;
			this->tenClosestCitiesButton->Text = L"10 closest Cities!";
			this->tenClosestCitiesButton->UseVisualStyleBackColor = true;
			this->tenClosestCitiesButton->Click += gcnew System::EventHandler(this, &MainForm::tenClosestCitiesButton_Click);
			// 
			// displayAllButton
			// 
			this->displayAllButton->Location = System::Drawing::Point(444, 290);
			this->displayAllButton->Name = L"displayAllButton";
			this->displayAllButton->Size = System::Drawing::Size(75, 34);
			this->displayAllButton->TabIndex = 30;
			this->displayAllButton->Text = L"Display All Cities";
			this->displayAllButton->UseVisualStyleBackColor = true;
			this->displayAllButton->Click += gcnew System::EventHandler(this, &MainForm::displayAllButton_Click);
			// 
			// MainForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->BackColor = System::Drawing::SystemColors::ButtonHighlight;
			this->ClientSize = System::Drawing::Size(587, 501);
			this->Controls->Add(this->displayAllButton);
			this->Controls->Add(this->tenClosestCitiesButton);
			this->Controls->Add(this->searchButton);
			this->Controls->Add(this->outputLabel);
			this->Controls->Add(this->outputTextBox);
			this->Controls->Add(this->cdcSecondCityCountryTextEntry);
			this->Controls->Add(this->cdcSecondCityNameTextEntry);
			this->Controls->Add(this->cdcSecondCityCountryLabel);
			this->Controls->Add(this->cdcSecondCityNameLabel);
			this->Controls->Add(this->cdcCalculateButton);
			this->Controls->Add(this->maintenanceEditButton);
			this->Controls->Add(this->removeCityButton);
			this->Controls->Add(this->addCityButton);
			this->Controls->Add(this->maintenanceCityLongditudeTextEntry);
			this->Controls->Add(this->maintenanceCityLongditudeLabel);
			this->Controls->Add(this->maintenanceCityLatitudeTextEntry);
			this->Controls->Add(this->maintenanceCityLatitudeLabel);
			this->Controls->Add(this->maintenanceCityCountryTextEntry);
			this->Controls->Add(this->cdcFirstCityCountryTextEntry);
			this->Controls->Add(this->cdcFirstCityNameTextEntry);
			this->Controls->Add(this->maintenanceCityNameTextField);
			this->Controls->Add(this->maintenanceEntryCountryLabel);
			this->Controls->Add(this->cdcEntryFirstCityCountryLabel);
			this->Controls->Add(this->maintenanceCityNameLabel);
			this->Controls->Add(this->cdcEntryFirstCityNameLabel);
			this->Controls->Add(this->maintenanceTitleLabel);
			this->Controls->Add(this->distanceCalculatorTitleLabel);
			this->Controls->Add(this->titleLabel);
			this->Controls->Add(this->textBox1);
			this->Controls->Add(this->textBox2);
			this->Controls->Add(this->textBox3);
			this->Font = (gcnew System::Drawing::Font(L"Segoe UI", 8.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(0)));
			this->Name = L"City Distance Calculator";
			this->Text = L"City Distance Calculator";
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion

	/**
 	 * Actions to be completed when the calculate button is pressed
 	 * Validates the user input and makes sure the cities entered exist
 	 * @param System::Object^
 	 * @param System::EventArgs^ 
 	 **/
    private: System::Void cdcCalculateButton_Click(System::Object^  sender, System::EventArgs^  e) {
        //converting input form first city name to regular string from system string
        string city1Name;
        msclr::interop::marshal_context context;
        city1Name = context.marshal_as<string>(this->cdcFirstCityNameTextEntry->Text);
        
        //converting input form first city country to regular string from system string
        string city1Country;
        city1Country = context.marshal_as<string>(this->cdcFirstCityCountryTextEntry->Text);
        
        //converting input form second city name to regular string from system string
        string city2Name;
        city2Name = context.marshal_as<string>(this->cdcSecondCityNameTextEntry->Text);
        
        //converting input form second city country to regular string from system string
        string city2Country;
        city2Country = context.marshal_as<string>(this->cdcSecondCityCountryTextEntry->Text);
        
        //Validating the entry
        if (!((city1Name.compare("")!=0)||(city1Name.compare(" ")!=0)||(city1Country.compare("")!=0)||(city1Country.compare(" ")!=0)||(city2Name.compare("")!=0)||(city2Name.compare(" ")!=0)||(city2Country.compare("")!=0)||(city2Country.compare(" ")!=0))) 
		{
            this->outputTextBox->Text = " Make sure the fields are not left blank";
            
        } else 
		{
            
            City* c1 = city_list.searchList(city1Name, city1Country);
            City* c2 = city_list.searchList(city2Name, city2Country);
            
            //Checking to make sure the search was successfull
            if ((c1->getLatitude2() == 0.0)||(c2->getLatitude2() == 0.0))
			{
                this->outputTextBox->Text = " One or more cities not found, please try again";
            } else 
			{
                //converting back to system strings for output to text area
                String^ city1SystemString = gcnew String(city1Name.c_str());
                String^ city2SystemString = gcnew String(city2Name.c_str());
                this->outputTextBox->Text = " The distance between " + city1SystemString + " and " + city2SystemString + " is: ";
                calcDistance(c1, c2);
                
            }
        }
    }

/**
 * Calculates the distance between two cities and prints the result to the screen
 * @param City*		The first city
 * @param City*		The second city
 **/
private: void calcDistance(City* city1, City* city2){
	
#define PI 3.14159265358979323846
    
	double distance;
	double s;
    
	distance = sin(city1->getLatitude())*sin(city2->getLatitude()) + cos(city1->getLatitude())*cos(city2->getLatitude())*cos(city1->getLongditude()-city2->getLongditude());
	distance = acos(distance);
	distance = distance * 180 / PI;
	cout << distance << endl;
	s = (6371*PI*distance)/180;
	
	// converts s from a double to a system string for output
	string sString;
	ostringstream convert;
	convert << s;
	sString = convert.str();
	String^ sSystemString = gcnew String(sString.c_str());
	this->outputTextBox->Text += s + "km";
}

/**
 * Calculates the distance between two cities and returns the result as a double
 * @param City*		The first city
 * @param City*		The second city
 * @return 		The distance as a double
 **/
private: double calcDistance2(City* city1, City* city2){
	
#define PI 3.14159265358979323846
    
	double distance;
	double s;
    
	distance = sin(city1->getLatitude())*sin(city2->getLatitude()) + cos(city1->getLatitude())*cos(city2->getLatitude())*cos(city1->getLongditude()-city2->getLongditude());
	distance = acos(distance);
	distance = distance * 180 / PI;
	
	s = (6371*PI*distance)/180;
	
	return s;
}
    
/**
 * This calculates the 10 closest cities to the city entered
 * It searches a temporary list and removes an element if it is the closest before repeating
 * Ouputs the results to the textbox on screen
 * @param City*		The City to be calculated
 **/
private: void calculateClosest(City *city1) {
	Link* tempLink;
    City* tempCity;
    tempLoadFile();
    double dist1;
    double temp = numeric_limits<double>::infinity();
    double cities[10] = {temp, temp, temp, temp, temp, temp, temp, temp, temp, temp};
    temp_list.removeObject(city1->getName(), city1->getCountry());
    outputTextBox->Text = "";
    for (int x = 0; x<10;x++){
        for(tempLink = temp_list.getHeadpointer() -> getNextLink(); tempLink != NULL; tempLink = tempLink -> getNextLink()){
 
            dist1 = calcDistance2(city1, tempLink->getCurrentObject());
            if (dist1 < cities[x]) {
                cities[x] = dist1;
                tempCity = tempLink->getCurrentObject();
            }
        }
        
		// output a list of 10 closest cities
		
		string cityName = tempCity->getName();
		string cityCountry = tempCity->getCountry();
		
		String^ cityNameSystemString = gcnew String(cityName.c_str());
		String^ cityCountrySystemString = gcnew String(cityCountry.c_str());
		ostringstream convert;
		convert << cities[x];
		string cities = convert.str();
		String^ citiesSystemString = gcnew String(cities.c_str());
		outputTextBox->Text += " " + cityNameSystemString + ", " + cityCountrySystemString + " : " + citiesSystemString + " km\r\n";
		
		temp_list.removeObject(tempCity->getName(), tempCity->getCountry());
        
    }
}

/**
 * Loads lines from the text file until all loaded
 * Loads them into the main linked list
 **/
private: void loadFile() {
	
	string line="";
	ifstream myFile("cities.txt");
    
    if (myFile.is_open()) {
        while (getline(myFile,line)) {
			
			if (!line.empty()) {
				splitLine(line, "Main");
			}
            
        }
		myFile.close();
    } else {
		cout << "Unable to read file";
	}
}
    
/**
 * Loads lines from the text file until all loaded
 * Loads them into the temp linked list used for the calculations of closest cities
 **/
private: void tempLoadFile() {
	string line="";
	ifstream myFile("cities.txt");
    
    if (myFile.is_open()) {
        while (getline(myFile,line)) {
			
			if (!line.empty()) {
				splitLine(line, "Temp");
			}
            
        }
		myFile.close();
    } else {
		cout << "Unable to read file";
	}
}

/**
 * Split Line function splits the line from the file into it's individual components
 * @param string	The line to be split
 * @param string	The list, either temp list or the main list
 **/
private: void splitLine(string line, string list) {
    string cName;
    string cCountry;
    string temp;
    size_t index;
    double cLat;
    double cLong;
    
    cout << "Line: " + line + '\n';
    
    index = line.find(",");
    cName = line.substr(0, index);
    temp = line.substr(index+2, line.length());
    
    index = temp.find(",");
    cCountry = temp.substr(0, index);
    temp = temp.substr(index+2, temp.length());
    
    index = temp.find(",");
    cLat = atof(temp.substr(0, index).c_str());;
    temp = temp.substr(index+2, temp.length());
    
    cLong = atof(temp.c_str());
	
    
    if (list.compare("Main")==0) {
        city_list.addObject(new City(cName, cCountry, cLat, cLong));
    } else if (list.compare("Temp")==0) {
		temp_list.addObject(new City(cName, cCountry, cLat, cLong));
    }
}
    
/**
 * Actions to be completed when the add city button is pressed
 * Validates the user input then adds the new city to the list
 * Then saves the updated list to the Text File
 * @param System::Object^
 * @param System::EventArgs^ 
 **/
private: System::Void addCityButton_Click(System::Object^  sender, System::EventArgs^  e) {
    // converts system string to regular string
    string cityName;
    bool check = false;
    msclr::interop::marshal_context context;
    cityName = context.marshal_as<string>(this->maintenanceCityNameTextField->Text);
    
    string cityCountry = context.marshal_as<string>(this->maintenanceCityCountryTextEntry->Text);
    
    // convert latitude system string to string, then convert string to double
    string cityLatitude = context.marshal_as<string>(this->maintenanceCityLatitudeTextEntry->Text);
    double cityLatitudeDouble = atof(cityLatitude.c_str());
    
    // convert longditude system string to string, then convert string to double
    string cityLongditude = context.marshal_as<string>(this->maintenanceCityLongditudeTextEntry->Text);
    double cityLongditudeDouble = atof(cityLongditude.c_str());
    
    
    if ((cityLatitudeDouble == NULL)||(cityLongditudeDouble == NULL))
	{
        this->outputTextBox->Text = "Make sure all fields are filled";  
    } 
	else 
	{

		 if (!((cityLatitude.compare("")!=0)||(cityLatitude.compare(" ")!=0)||(cityLongditude.compare("")!=0)||(cityLongditude.compare(" ")))) 
				{
					this->outputTextBox->Text = " Make sure all fields are filled";
				} else {
				
					City* city1 = city_list.searchList(cityName, cityCountry);

					if (city1->getName().compare(cityName)==0) {
						this->outputTextBox->Text = "City already in list"; 
					} else {

						city_list.addObject(new City(cityName, cityCountry, cityLatitudeDouble, cityLongditudeDouble));
						String^ cityNameSystemString = gcnew String(cityName.c_str());
						this->outputTextBox->Text = " " + cityNameSystemString + " was added to the list.";	
						saveToFile();
		}

		 }
		
        
    }
    
}

/**
 * Actions to be completed when the removes city button is pressed
 * Validates the user input and removes the city from the list, then saves the updated list to the file
 * @param System::Object^
 * @param System::EventArgs^ 
 **/
private: System::Void removeCityButton_Click(System::Object^  sender, System::EventArgs^  e) {
    // converts cityName system string to regular string
    string cityName;
    msclr::interop::marshal_context context;
    cityName = context.marshal_as<string>(this->maintenanceCityNameTextField->Text);
    
    // converts cityCountry system string to regular string
    string cityCountry = context.marshal_as<string>(this->maintenanceCityCountryTextEntry->Text);
    
    //Validating the input
    if (!((cityName.compare("")!=0)||(cityName.compare(" ")!=0)||(cityCountry.compare("")!=0)||(cityCountry.compare(" ")))) {
        this->outputTextBox->Text = "Make sure all fields are filled";
        
    } else {
        
        //remove city from the list
        bool removed = city_list.removeObject(cityName, cityCountry);
        if (removed) {
            //confirm removal
            String^ cityNameSystemString = gcnew String(cityName.c_str());
            this->outputTextBox->Text = " " + cityNameSystemString + " was removed from the list.";
			saveToFile();
        } else {
            this->outputTextBox->Text = "City not removed, please try again";
        }
    }
}

/**
 * Actions to be completed when the edit city button is pressed
 * Validates the user input then searches the list for the city to be edited
 * This populates the GUI textfields with the current attributes of the City
 * Updates the values
 * Then saves the updated list to the Text File
 * @param System::Object^
 * @param System::EventArgs^ 
 **/
private: System::Void maintenanceEditButton_Click(System::Object^  sender, System::EventArgs^  e) {
    // converts system string to regular string
    string cityName;
    msclr::interop::marshal_context context;
    cityName = context.marshal_as<string>(this->maintenanceCityNameTextField->Text);
    
    string cityCountry = context.marshal_as<string>(this->maintenanceCityCountryTextEntry->Text);
    
    //Validating the input
    if (!((cityName.compare("")!=0)||(cityName.compare(" ")!=0)||(cityCountry.compare("")!=0)||(cityCountry.compare(" ")))) {
        this->outputTextBox->Text = " Make sure all fields are filled";
    } else {
        
        // searches the list for the city the user has entered
        City* cityToEdit = city_list.searchList(cityName, cityCountry);
        
        //Checking to make sure the search was successfull
        if ((cityToEdit->getLatitude2() == 0.0)||(cityToEdit->getLatitude2() == 0.0))
		{
            this->outputTextBox->Text = " City not found, please try again";
        } else 
		{
			
			City* city1 = city_list.searchList(cityName, cityCountry);


            // edits city name
            cityToEdit->setName(cityName);
            
            // edits city country
            cityToEdit->setCountry(cityCountry);
            
            // convert latitude system string to string, then convert string to double
            string cityLatitude = context.marshal_as<string>(this->maintenanceCityLatitudeTextEntry->Text);
            double cityLatitudeDouble = atof(cityLatitude.c_str());

            // convert longditude system string to string, then convert string to double
            string cityLongditude = context.marshal_as<string>(this->maintenanceCityLongditudeTextEntry->Text);
            double cityLongditudeDouble = atof(cityLongditude.c_str());

			    if (!((cityLatitude.compare("")!=0)||(cityLatitude.compare(" ")!=0)||(cityLongditude.compare("")!=0)||(cityLongditude.compare(" ")))) 
				{
        this->outputTextBox->Text = " Make sure all fields are filled";
				} else {
					// edits city longditude
					cityToEdit->setLongditude(cityLongditudeDouble);

					// edits city latitude
					cityToEdit->setLatitude(cityLatitudeDouble);

					saveToFile();
				}
				}
            
			}
        }
    

/**
 * Actions to be completed when the search button is pressed
 * Validates the name and country text fields and searches the list for the name in these fields
 * populates the GUI Textfields with the information about the city searched for
 * If the city is not found the GUI will output a error message
 * @param System::Object^
 * @param System::EventArgs^ e
 **/
private: System::Void searchButton_Click(System::Object^  sender, System::EventArgs^  e) {
    // converts system string to regular string
    string cityName;
    msclr::interop::marshal_context context;
    cityName = context.marshal_as<string>(this->maintenanceCityNameTextField->Text);
    
    string cityCountry = context.marshal_as<string>(this->maintenanceCityCountryTextEntry->Text);
    
    if (!((cityName.compare("")!=0)||(cityName.compare(" ")!=0)||(cityCountry.compare("")!=0)||(cityCountry.compare(" ")))) {
        this->outputTextBox->Text = "Make sure all fields are filled";
    } else {
        // searches the list for the city the user has entered
        City* citySearched =  city_list.searchList(cityName, cityCountry);
        
        //Checking to make sure the search was successfull
        if ((citySearched->getLatitude2() == 0.0)||(citySearched->getLatitude2() == 0.0)){
            this->outputTextBox->Text = " City not found, please try again";
        } else {
            double citySearchedLat = citySearched->getLatitude();
            double citySearchedLong = citySearched->getLongditude();
            
            string citySearchedLatString;
            string citySearchedLongString;
            
            // converts city longditude as stored in radians to city latitude system string for output in degrees
            ostringstream convert;
            convert << citySearchedLat * 180 / PI;
            citySearchedLatString = convert.str();
            String^ citySearchedLatSString = gcnew String(citySearchedLatString.c_str());
            this->maintenanceCityLatitudeTextEntry->Text = citySearchedLatSString;
            
            // converts city longditude as stored in radians to city latitude system string for output in degrees
            ostringstream convert2;
            convert2 << citySearchedLong * 180 / PI;
            citySearchedLongString = convert2.str();
            String^ citySearchedLongSString = gcnew String(citySearchedLongString.c_str());
            this->maintenanceCityLongditudeTextEntry->Text = citySearchedLongSString;
        }
    }
}

/**
 * Actions to be completed when the closest cities button is pressed
 * Validates the user input of the city name and the city country and searches the list to make sure the 
 * city exists
 * Then calls the calculate closest functon
 * @param System::Object^
 * @param System::EventArgs^ e
 **/
private: System::Void tenClosestCitiesButton_Click(System::Object^  sender, System::EventArgs^  e) {
    // converts system string to regular string
    string cityName;
    msclr::interop::marshal_context context;
    cityName = context.marshal_as<string>(this->cdcFirstCityNameTextEntry->Text);
    
    string cityCountry = context.marshal_as<string>(this->cdcFirstCityCountryTextEntry->Text);
    
    if (!((cityName.compare("")!=0)||(cityName.compare(" ")!=0)||(cityCountry.compare("")!=0)||(cityCountry.compare(" ")))) {
		
        this->outputTextBox->Text = "Make sure all fields are filled";
    } else {
        // searches the list for the city the user has entered
        City* cityToCalculate = city_list.searchList(cityName, cityCountry);
		double cityToCalculateLat = cityToCalculate->getLatitude2();
		string cityToCalculateLatString;
        string citySearchedLongString;
            
        // converts city longditude as stored in radians to city latitude system string for output in degrees
        ostringstream convert;
        convert << cityToCalculateLat;
        cityToCalculateLatString = convert.str();
        String^ cityToCalculateLatSString = gcnew String(cityToCalculateLatString.c_str());
        if ((cityToCalculate->getLatitude2() == 0.0)||(cityToCalculate->getLatitude2() == 0.0)){

            this->outputTextBox->Text = " City not found, please try again";
        } else {
            calculateClosest(cityToCalculate);
        }
    }
    
}

/**
 * Save to file function saves the entire list to the textfile
 * Each city goes on it's own line
 **/
private: void saveToFile() {
	ofstream myFile;
	myFile.open("cities.txt", fstream::out);
    Link* tempLink;
	for(tempLink = city_list.getHeadpointer() -> getNextLink(); tempLink != NULL; tempLink = tempLink -> getNextLink()){
		myFile << tempLink->getCurrentObject()->getName() << ", ";
		myFile << tempLink->getCurrentObject()->getCountry() << ", ";
		myFile << tempLink->getCurrentObject()->getLatitude2();
		myFile << ", ";
		myFile << tempLink->getCurrentObject()->getLongditude2();
		myFile << "\n";
	}
	myFile.close();
}

/**
 * Actions to be completed when the display all button is pressed
 * Output all the cities in the list to the text box on the GUI
 * @param System::Object^
 * @param System::EventArgs^ e
 **/
private: System::Void displayAllButton_Click(System::Object^  sender, System::EventArgs^  e) {
			 string name;
			 string country;
			 double latitude;
			 double longditude;
			 Link* tempLink;

			 for(Link* tempLink = city_list.getHeadpointer(); tempLink != NULL; tempLink = tempLink -> getNextLink())
			 {
				 string latitudeString;
				 string longditudeString;
				 
				 name = tempLink->getCurrentObject()->getName();
				 //convert string to system string
				 String^ nameSString = gcnew String(name.c_str());

				 country = tempLink->getCurrentObject()->getCountry();
				 String^ countrySString = gcnew String(name.c_str());

				 latitude = tempLink->getCurrentObject()->getLatitude2();
				 ostringstream convert;
				 convert << latitude;
				 latitudeString = convert.str();
			     String^ latitudeSString = gcnew String(latitudeString.c_str());
				 
				 longditude = tempLink->getCurrentObject()->getLongditude2();
				 ostringstream convert2;
				 convert2 << longditude;
				 longditudeString = convert.str();
			     String^ longditudeSString = gcnew String(longditudeString.c_str());

				 outputTextBox->Text += " " + nameSString + ", " + countrySString + ", " + latitudeSString + ", " + longditudeSString + "\r\n";
			 }
		 }
};
}
