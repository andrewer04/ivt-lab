package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  @Before
  public void init(){
    //this.ship = new GT4500();
    this.primaryTorpedoStore = Mockito.mock(TorpedoStore.class);
    this.secondaryTorpedoStore = Mockito.mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);    
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
	when(primaryTorpedoStore.isEmpty()).thenReturn(false);
	when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
	when(primaryTorpedoStore.fire(1)).thenReturn(true);
	when(secondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE); 

    // Assert
    //assertEquals(true, result);
    verify(primaryTorpedoStore,times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1); 
    
  }
  
  @Test
  public void primartyEmptyTest(){
    // Arrange
	when(primaryTorpedoStore.isEmpty()).thenReturn(true);
	when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
	when(primaryTorpedoStore.fire(1)).thenReturn(false);
	when(secondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE); 

    // Assert
    //assertEquals(true, result);
    verify(secondaryTorpedoStore, times(2)).fire(1); 
  }
  
  @Test
  public void secondaryEmptyTest(){
    // Arrange
	when(primaryTorpedoStore.isEmpty()).thenReturn(false);
	when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
	when(primaryTorpedoStore.fire(1)).thenReturn(true);
	when(secondaryTorpedoStore.fire(1)).thenReturn(false);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE); 

    // Assert
    //assertEquals(true, result);
    verify(primaryTorpedoStore, times(2)).fire(1); 
  }
  
  @Test
  public void allEmptyTest(){
    // Arrange
	when(primaryTorpedoStore.isEmpty()).thenReturn(true);
	when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
	when(primaryTorpedoStore.fire(1)).thenReturn(false);
	when(secondaryTorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(primaryTorpedoStore, times(0)).fire(1); 
  }
  
  @Test
  public void primaryTest(){
    // Arrange
	when(primaryTorpedoStore.isEmpty()).thenReturn(false);
	when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
	when(primaryTorpedoStore.fire(1)).thenReturn(true);
	when(secondaryTorpedoStore.fire(1)).thenReturn(false);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    
    //Arrange2
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    
    //Act2
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(false, result);
    verify(primaryTorpedoStore, times(1)).fire(1); 
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

}
